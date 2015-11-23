package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.CardEvent;
import com.thoughtworks.lhli.lovelive_rock.CardManager;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.SmallCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardActivity extends AppCompatActivity {

    @Bind(R.id.small_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        CardManager cardManager = new CardManager(new ArrayList<Card>(), CardActivity.this);

        try {
            cardManager.getCardById("315");
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CardActivity.this, CardDetailActivity.class));
            }
        });
    }

    public void onEvent(CardEvent cardEvent) {
        listView.setAdapter(new SmallCardListAdapter(CardActivity.this, cardEvent.getCardList()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}