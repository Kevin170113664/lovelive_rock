package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.support.annotation.NonNull;

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

    private List<EventModel> eventModelList;
    private Context context;
    DatabaseManager databaseManager;

    public EventManager(List<EventModel> eventModelList, Context context) {
        this.eventModelList = eventModelList;
        this.context = context;
        this.databaseManager = new DatabaseManager(context);
    }

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }

    public void getLatestEvent() throws IOException {
        EventModel eventModel = databaseManager.queryLatestEvent("Score Match Round 22");

        if (eventModel != null && eventModel.getJapaneseName() != null) {
            eventModelList.add(eventModel);
            EventBus.getDefault().post(new EventEvent(eventModelList));
        } else if (CardManager.isNetworkAvailable(context)) {
            Call<MultipleEvents> call =
                    Retrofit.getInstance().getEventService().getLatestEvent("-beginning", 1);
            call.enqueue(getLatestCallback());
        } else {
            System.out.print("Network is not available.");
        }
    }

    @NonNull
    private Callback<MultipleEvents> getLatestCallback() {
        return new Callback<MultipleEvents>() {
            @Override
            public void onResponse(Response<MultipleEvents> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    eventModelList.addAll(Arrays.asList(response.body().getResults()));
                    databaseManager.cacheLatestEvent(eventModelList);
                    EventBus.getDefault().post(new EventEvent(eventModelList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getLatestCallback failed.");
            }
        };
    }
}