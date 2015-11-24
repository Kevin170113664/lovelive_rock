package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.EventEvent;
import com.thoughtworks.lhli.lovelive_rock.model.Event;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleEvents;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class EventManager {

    private List<Event> eventList;
    private Context context;
    DatabaseManager databaseManager;

    public EventManager(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
        this.databaseManager = new DatabaseManager(context);
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void getLatestEvent() throws IOException {
        Event event = databaseManager.getLatestEventFromCache("Score Match Round 22");

        if (event != null && event.getJapaneseName() != null) {
            eventList.add(event);
            EventBus.getDefault().post(new EventEvent(eventList));
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
                    eventList.addAll(Arrays.asList(response.body().getResults()));
                    databaseManager.cacheEvent(eventList);
                    EventBus.getDefault().post(new EventEvent(eventList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getLatestCallback failed.");
            }
        };
    }
}