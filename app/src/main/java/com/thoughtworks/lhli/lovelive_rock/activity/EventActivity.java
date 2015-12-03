package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.common.collect.Lists;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.EventListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.EventListEvent;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class EventActivity extends BaseActivity {

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.event_list)
    protected ListView listView;

    private List<EventModel> eventModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif()
                .into((ImageView) findViewById(R.id.loading_icon));

        new Thread(new LoadActivityData(this)).start();
    }

    public void onEventMainThread(EventListEvent eventListEvent) throws IOException {
        loadingIcon.setVisibility(View.GONE);

        eventModelList = eventListEvent.getEventModelList();
        eventModelList = Lists.reverse(eventModelList);
        listView.setAdapter(new EventListAdapter(EventActivity.this, eventModelList));

    }
}
