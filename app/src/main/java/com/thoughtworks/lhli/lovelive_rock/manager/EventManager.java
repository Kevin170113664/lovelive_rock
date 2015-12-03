package com.thoughtworks.lhli.lovelive_rock.manager;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.EventListEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.LatestEventEvent;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleEvents;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

public class EventManager {

    private String latestEventName;
    private List<EventModel> eventModelList;
    DatabaseManager databaseManager;

    public EventManager(List<EventModel> eventModelList) {
        this.eventModelList = eventModelList;
        this.databaseManager = new DatabaseManager();
        this.latestEventName = LoveLiveApp.getInstance().getLatestEventName();
    }

    public void getEventModelList() throws IOException {
        List<EventModel> eventModelListFromDB = databaseManager.queryAllEvents();
        EventBus.getDefault().post(new EventListEvent(eventModelListFromDB));
    }

    public void getLatestEvent() throws IOException {
        if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            fetchEventFromInternet();
        } else {
            queryEventFromDatabase();
        }
    }

    private void fetchEventFromInternet() throws IOException {
        Call<MultipleEvents> call =
                Retrofit.getInstance().getEventService().getLatestEvent("-beginning", 1);
        Response<MultipleEvents> response = call.execute();
        if (response.isSuccess()) {
            eventModelList.addAll(Arrays.asList(response.body().getResults()));
            EventBus.getDefault().post(new LatestEventEvent(eventModelList));
            LoveLiveApp.getInstance().setLatestEventName(eventModelList.get(0).getJapaneseName());
        }
    }

    private void queryEventFromDatabase() {
        EventModel eventModel = databaseManager.queryLatestEvent(latestEventName);
        if (eventModel != null && eventModel.getJapaneseName() != null) {
            eventModelList.add(eventModel);
            EventBus.getDefault().post(new LatestEventEvent(eventModelList));
        }
    }
}