package com.thoughtworks.lhli.lovelive_rock.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.manager.EventManager;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadMainActivityData extends AsyncTask<Void, Void, List<EventModel>> {

    private Activity activity;

    public LoadMainActivityData(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<EventModel> doInBackground(Void... voids) {
        final EventManager eventManager = new EventManager(new ArrayList<EventModel>(), activity);
        try {
            List<EventModel> eventModelList = eventManager.getLatestEvent();
            return eventModelList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<EventModel> eventModelList) {
        super.onPostExecute(eventModelList);
        Picasso.with(activity)
                .load(eventModelList.get(0).getImage())
                .into((ImageView) activity.findViewById(R.id.latest_event_image));
    }
}
