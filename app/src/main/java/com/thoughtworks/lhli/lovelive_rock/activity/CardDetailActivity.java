package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardDetailActivity extends BaseActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    private CardModel cardModel;
    private List<CardModel> cardModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
        loadCardData();
    }

    private void loadCardData() {
        cardModel = (CardModel) getIntent().getSerializableExtra("CardModel");
        cardModelList.add(cardModel);
        listView.setAdapter(new MediumCardListAdapter(CardDetailActivity.this, cardModelList));
    }
}
