package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.MediumCardEvent;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardDetailActivity extends BaseActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif()
                .into((ImageView) findViewById(R.id.loading_icon));
        new LoadActivityData(this).execute();
    }

    public void onEventMainThread(MediumCardEvent mediumCardEvent) {
        findViewById(R.id.loading_icon).setVisibility(View.GONE);
        listView.setAdapter(new MediumCardListAdapter(CardDetailActivity.this,
                mediumCardEvent.getCardModelList()));
    }
}
