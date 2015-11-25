package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.bus.MainActivityEvent;
import com.thoughtworks.lhli.lovelive_rock.task.LoadMainActivityData;

import java.io.IOException;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif()
                .into((ImageView) findViewById(R.id.loading_icon));
        new LoadMainActivityData(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onEventMainThread(MainActivityEvent mainActivityEvent) throws IOException {
        findViewById(R.id.loading_icon).setVisibility(View.GONE);
        if (mainActivityEvent.getHashMap().get("EventImage") != null) {
            Picasso.with(this)
                    .load(mainActivityEvent.getHashMap().get("EventImage"))
                    .into((ImageView) findViewById(R.id.latest_event_image));
        }
        if (mainActivityEvent.getHashMap().get("NonIdolizedSrImage") != null) {
            Picasso.with(this)
                    .load(mainActivityEvent.getHashMap().get("NonIdolizedSrImage"))
                    .into((ImageView) findViewById(R.id.latest_event_Sr_image));
        }
        if (mainActivityEvent.getHashMap().get("IdolizedSrImage") != null) {
            Picasso.with(this)
                    .load(mainActivityEvent.getHashMap().get("IdolizedSrImage"))
                    .into((ImageView) findViewById(R.id.latest_event_idolized_Sr_image));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_song || id == R.id.action_event) {
            return true;
        }
        if (item.getItemId() == R.id.action_card) {
            startActivity(new Intent(this, CardActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}