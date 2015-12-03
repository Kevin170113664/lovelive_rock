package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class EventActivity extends BaseActivity {

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.event_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
}
