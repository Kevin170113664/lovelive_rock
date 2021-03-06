package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.CardGridView;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.CardDetailSmallCardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardDetailActivity extends BaseActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Bind(R.id.grid_view)
    protected CardGridView gridView;

    @Bind(R.id.feed_guide_text)
    protected TextView feedGuideText;

    private CardModel cardModel;
    private List<CardModel> cardModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        loadCardData();
    }

    private void loadCardData() {
        cardModel = (CardModel) getIntent().getSerializableExtra("CardModel");
        List<CardModel> cardDetailList = new ArrayList<>();
        cardDetailList.add(cardModel);
        listView.setAdapter(new MediumCardListAdapter(CardDetailActivity.this, cardDetailList));

        if (cardModel.isPromo()) {
            new Thread(new LoadActivityData(this, cardModel.getSkill())).start();
        }
    }

    public void onEventMainThread(CardDetailSmallCardEvent cardDetailSmallCardEvent) {
        cardModelList = cardDetailSmallCardEvent.getCardModelList();

        feedGuideText.setVisibility(View.VISIBLE);
        gridView.setAdapter(new GridCardListAdapter(CardDetailActivity.this, cardModelList, false));
        setGridViewItemClickListener();
    }

    private void setGridViewItemClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardDetailActivity.this, CardDetailActivity.class);
                CardModel cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position);
                if (cardModel.getCardId() == null) {
                    cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position + 1);
                }
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });
    }
}
