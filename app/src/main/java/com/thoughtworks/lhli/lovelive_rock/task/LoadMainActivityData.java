package com.thoughtworks.lhli.lovelive_rock.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.bus.EventEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.MainActivityEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.MainCardEvent;
import com.thoughtworks.lhli.lovelive_rock.manager.CardManager;
import com.thoughtworks.lhli.lovelive_rock.manager.EventManager;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

public class LoadMainActivityData extends AsyncTask<Void, Void, String[]> {

    private Activity activity;
    private List<EventModel> eventModelList;
    private List<CardModel> cardModelList;

    public LoadMainActivityData(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String[] doInBackground(Void... voids) {
        EventBus.getDefault().register(this);
        eventModelList = new ArrayList<>();
        cardModelList = new ArrayList<>();
        final EventManager eventManager = new EventManager(new ArrayList<EventModel>(), activity);
        try {
            eventManager.getLatestEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[3];
    }

    @Override
    protected void onPostExecute(String[] imageUrl) {
        super.onPostExecute(imageUrl);
        HashMap<String, String> imageUrlMap = new HashMap<>();
        imageUrlMap.put("EventImage", imageUrl[0]);
        imageUrlMap.put("NonIdolizedSrImage", imageUrl[1]);
        imageUrlMap.put("IdolizedSrImage", imageUrl[2]);

        EventBus.getDefault().post(new MainActivityEvent(imageUrlMap));
    }

    public void onEvent(EventEvent eventEvent) throws IOException {
        String[] imageUrl = new String[3];
        imageUrl[0] = eventEvent.getEventModelList().get(0).getImage();
        onPostExecute(imageUrl);

        final CardManager cardManager = new CardManager(new ArrayList<CardModel>(), activity);

        if (readLatestEventSrId().equals("0")) {
            Integer cardId = eventEvent.getEventModelList().get(0).getCards()[1];
            cardManager.getCardById(cardId.toString());
            saveLatestEventSrId(cardId.toString());
        } else {
            cardManager.getCardById(readLatestEventSrId());
        }
    }

    public void onEvent(MainCardEvent mainCardEvent) {
        String[] imageUrl = new String[3];
        imageUrl[1] = mainCardEvent.getCardModelList().get(0).getCardImage();
        imageUrl[2] = mainCardEvent.getCardModelList().get(0).getCardIdolizedImage();
        onPostExecute(imageUrl);
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
}
