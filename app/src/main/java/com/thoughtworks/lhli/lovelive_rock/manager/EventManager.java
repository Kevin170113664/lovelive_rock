package com.thoughtworks.lhli.lovelive_rock.manager;

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

    public EventManager(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void getLatestEvent() throws IOException {
        if (CardManager.isNetworkAvailable()) {
            Call<MultipleEvents> call =
                    Retrofit.getInstance().getEventService().getLatestEvent("-beginning", 1);
            call.enqueue(getLatestCallback());
        } else {
            System.out.print("Get all cards failed.");
        }
    }

    @NonNull
    private Callback<MultipleEvents> getLatestCallback() {
        return new Callback<MultipleEvents>() {
            @Override
            public void onResponse(Response<MultipleEvents> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    eventList.addAll(Arrays.asList(response.body().getResults()));
                    EventBus.getDefault().post(new EventEvent(eventList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getLatestCallback failed.");
            }
        };
    }

    private void cacheEvent() {
//        helper = new AbstractDaoMaster.( this, "notes-db", null);
//        db = helper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//        noteDao = daoSession.getNoteDao();
    }
}
