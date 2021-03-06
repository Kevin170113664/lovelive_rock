package com.thoughtworks.lhli.lovelive_rock.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.activity.CardActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.CardDetailActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.EventActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.MainActivity;
import com.thoughtworks.lhli.lovelive_rock.activity.SongActivity;
import com.thoughtworks.lhli.lovelive_rock.bus.LatestEventEvent;
import com.thoughtworks.lhli.lovelive_rock.manager.CardManager;
import com.thoughtworks.lhli.lovelive_rock.manager.EventManager;
import com.thoughtworks.lhli.lovelive_rock.manager.SongManager;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.io.IOException;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class LoadActivityData implements Runnable {

    private Activity activity;
    private String skill;

    public LoadActivityData(Activity activity) {
        this.activity = activity;
    }

    public LoadActivityData(Activity activity, String skill) {
        this.activity = activity;
        this.skill = skill;
    }

    private void loadCardActivityData() {
        CardManager cardManager = new CardManager(new ArrayList<CardModel>());
        try {
            cardManager.getAllCards();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cardManager.updateLatest20Cards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMainActivityData() {
        loadMaxCardNumber();
        EventManager eventManager = new EventManager(new ArrayList<EventModel>());
        try {
            LoveLiveApp.getInstance().setLatestEventName(readLatestEventName());
            eventManager.getLatestEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEventActivityData() {
        EventManager eventManager = new EventManager(new ArrayList<EventModel>());
        try {
            eventManager.getEventModelList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            eventManager.updateLatestThreeEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCardDetailActivityData() {
        CardManager cardManager = new CardManager(new ArrayList<CardModel>());
        cardManager.getCardBySkill(skill);
    }

    private void loadSongActivityData() {
        SongManager songManager = new SongManager(new ArrayList<SongModel>());
        try {
            songManager.getAllSongs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMaxCardNumber() {
        CardManager cardManager = new CardManager(new ArrayList<CardModel>());
        try {
            cardManager.getMaxCardNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLatestEventName(String lastEventName) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(activity.getString(R.string.latest_event_name), lastEventName);
        editor.apply();
    }

    private String readLatestEventName() {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(activity.getString(R.string.latest_event_name), activity.getString(R.string.event_default_value));
    }

    public void onEvent(LatestEventEvent latestEventEvent) throws IOException {
        saveLatestEventName(latestEventEvent.getEventModelList().get(0).getJapaneseName());
    }

    @Override
    public void run() {
        EventBus.getDefault().register(this);
        if (activity.getClass().equals(MainActivity.class)) {
            loadMainActivityData();
        } else if (activity.getClass().equals(CardActivity.class)) {
            loadCardActivityData();
        } else if (activity.getClass().equals(EventActivity.class)) {
            loadEventActivityData();
        } else if (activity.getClass().equals(CardDetailActivity.class)) {
            loadCardDetailActivityData();
        } else if (activity.getClass().equals(SongActivity.class)) {
            loadSongActivityData();
        }
    }
}
