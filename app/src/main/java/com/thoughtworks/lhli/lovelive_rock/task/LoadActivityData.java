package com.thoughtworks.lhli.lovelive_rock.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.activity.CardActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.MainActivity;
import com.thoughtworks.lhli.lovelive_rock.bus.EventEvent;
import com.thoughtworks.lhli.lovelive_rock.manager.CardManager;
import com.thoughtworks.lhli.lovelive_rock.manager.EventManager;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.io.IOException;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class LoadActivityData extends AsyncTask<Void, Void, Void> {

    private Activity activity;

    public LoadActivityData(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        EventBus.getDefault().register(this);
        if (activity.getClass().equals(MainActivity.class)) {
            loadMainActivityData();
        } else if (activity.getClass().equals(CardActivity.class)) {
            loadCardActivityData();
        }
        return null;
    }

    private void loadCardActivityData() {
        CardManager cardManager = new CardManager(new ArrayList<CardModel>(), activity);
        try {
            cardManager.getAllCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMainActivityData() {
        EventManager eventManager = new EventManager(new ArrayList<EventModel>(), activity);
        try {
            eventManager.getLatestEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
    }

    protected void saveLatestEventSrId(String cardId) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.latest_event_Sr_id), cardId);
        editor.apply();
    }

    protected String readLatestEventSrId() {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(activity.getString(R.string.latest_event_Sr_id), "0");
    }

    public void onEvent(EventEvent eventEvent) throws IOException {
        CardManager cardManager = new CardManager(new ArrayList<CardModel>(), activity);

        if (readLatestEventSrId().equals("0")
                && eventEvent.getEventModelList().get(0).getCards() != null) {
            Integer cardId = eventEvent.getEventModelList().get(0).getCards()[1];
            cardManager.getCardById(cardId.toString());
            saveLatestEventSrId(cardId.toString());
        } else {
            cardManager.getCardById(readLatestEventSrId());
        }
    }
}
