package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.EventListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.EventListEvent;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
        sortEventList();
        listView.setAdapter(new EventListAdapter(EventActivity.this, eventModelList));
        setListViewOnItemClickListener();
    }

    private void setListViewOnItemClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventActivity.this, EventDetailActivity.class);
                EventModel eventModel = ((List<EventModel>) parent.getAdapter().getItem(0)).get(position);
                intent.putExtra("EventModel", eventModel);
                startActivity(intent);
            }
        });
    }

    private void sortEventList() {
        Collections.sort(eventModelList, new Comparator<EventModel>() {
            @Override
            public int compare(EventModel firstEvent, EventModel secondEvent) {
                return secondEvent.getEnd().compareTo(firstEvent.getEnd());
            }

            @Override
            public boolean equals(Object object) {
                return false;
            }
        });
    }
}
