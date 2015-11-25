package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.bus.MediumCardEvent;
import com.thoughtworks.lhli.lovelive_rock.manager.CardManager;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

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
        CardManager cardManager = new CardManager(new ArrayList<CardModel>(), CardDetailActivity.this);

        try {
            String cardId = getIntent().getStringExtra("cardId");
            cardManager.getCardById(cardId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEvent(MediumCardEvent mediumCardEvent) {
        listView.setAdapter(new MediumCardListAdapter(CardDetailActivity.this, mediumCardEvent.getCardModelList()));
    }
}
