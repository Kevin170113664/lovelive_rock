package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CardDetailActivity extends AppCompatActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        CardManager cardManager = new CardManager(new ArrayList<Card>(), CardDetailActivity.this);
        try {
            cardManager.getAllCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEvent(CardEvent cardEvent) {
        listView.setAdapter(new MediumCardListAdapter(CardDetailActivity.this, cardEvent.getCardList()));
    }
}
