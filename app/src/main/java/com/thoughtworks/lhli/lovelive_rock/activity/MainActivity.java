package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.CardEvent;
import com.thoughtworks.lhli.lovelive_rock.CardManager;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        CardManager cardManager = new CardManager(new ArrayList<Card>(), MainActivity.this);
        try {
            cardManager.getAllCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEvent(CardEvent cardEvent) {
        listView.setAdapter(new MediumCardListAdapter(MainActivity.this, cardEvent.getCardList()));
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
