package com.thoughtworks.lhli.lovelive_rock.manager;

import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.EventEvent;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleEvents;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
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

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }

    public void getLatestEvent() throws IOException {
        if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            Call<MultipleEvents> call =
                    Retrofit.getInstance().getEventService().getLatestEvent("-beginning", 1);
            call.enqueue(getLatestCallback());
        } else {
            EventModel eventModel = databaseManager.queryLatestEvent(latestEventName);
            if (eventModel != null && eventModel.getJapaneseName() != null) {
                eventModelList.add(eventModel);
                EventBus.getDefault().post(new EventEvent(eventModelList));
            }
        }
    }

    @NonNull
    private Callback<MultipleEvents> getLatestCallback() {
        return new Callback<MultipleEvents>() {
            @Override
            public void onResponse(Response<MultipleEvents> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    eventModelList.addAll(Arrays.asList(response.body().getResults()));
                    EventBus.getDefault().post(new EventEvent(eventModelList));
                    LoveLiveApp.getInstance().setLatestEventName(eventModelList.get(0).getJapaneseName());
//                    databaseManager.cacheLatestEvent(eventModelList);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getLatestCallback failed.");
            }
        };
    }
}