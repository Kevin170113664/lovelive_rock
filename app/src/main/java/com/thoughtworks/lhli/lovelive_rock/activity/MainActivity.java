package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.bus.CardEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.EventEvent;
import com.thoughtworks.lhli.lovelive_rock.manager.CardManager;
import com.thoughtworks.lhli.lovelive_rock.manager.EventManager;
import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.Event;

import java.io.IOException;
import java.util.ArrayList;

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
        final EventManager eventManager = new EventManager(new ArrayList<Event>(), this);

        try {
            eventManager.getLatestEvent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEvent(EventEvent eventEvent) throws IOException {
        Picasso.with(this)
                .load(eventEvent.getEventList().get(0).getImage())
                .into((ImageView) findViewById(R.id.latest_event_image));

        final CardManager cardManager = new CardManager(new ArrayList<Card>(), this);

        if (readLatestEventSrId().equals("0")) {
            Integer cardId = eventEvent.getEventList().get(0).getCards()[1];
            cardManager.getCardById(cardId.toString());
        } else {
            cardManager.getCardById(readLatestEventSrId());
        }
        saveLatestEventSrId(eventEvent);
    }

    public void onEvent(CardEvent cardEvent) {
        Picasso.with(this)
                .load(cardEvent.getCardList().get(0).getCardImage())
                .into((ImageView) findViewById(R.id.latest_event_Sr_image));

        Picasso.with(this)
                .load(cardEvent.getCardList().get(0).getCardIdolizedImage())
                .into((ImageView) findViewById(R.id.latest_event_idolized_Sr_image));
    }

    protected void saveLatestEventSrId(EventEvent eventEvent) {
        if (eventEvent.getEventList().get(0).getCards() != null) {
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.latest_event_Sr_id),
                    String.format("%s", eventEvent.getEventList().get(0).getCards()[1]));
            editor.apply();
        }
    }

    protected String readLatestEventSrId() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.latest_event_Sr_id), "0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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