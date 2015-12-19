package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends BaseActivity {

    @Bind(R.id.event_image)
    ImageView eventImage;

    @Bind(R.id.event_name)
    TextView eventName;

    @Bind(R.id.beginning_time)
    TextView beginningTime;

    @Bind(R.id.end_time)
    TextView endTime;

    @Bind(R.id.rank_1)
    TextView rank1;

    @Bind(R.id.points_1)
    TextView points1;

    @Bind(R.id.rank_2)
    TextView rank2;

    @Bind(R.id.points_2)
    TextView points2;

    @Bind(R.id.notes)
    TextView notes;

    @Bind(R.id.grid_view)
    protected GridView gridView;

    private EventModel eventModel;
    private CardModel nCardModel;
    private CardModel srCardModel;
    private List<CardModel> eventCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        setEventDetailData();
    }

    private void setEventDetailData() {
        readDataFromParentIntent();
        setEventCardGrid();
        setEventSummary();
    }

    private void setEventSummary() {
        Picasso.with(this)
                .load(eventModel.getImage())
                .into(eventImage);

        setTextView(eventName, eventModel.getJapaneseName());
        setTextView(beginningTime, eventModel.getBeginning());
        setTextView(endTime, eventModel.getEnd());
        setTextView(rank1, eventModel.getJapaneseT1Rank());
        setTextView(points1, eventModel.getJapaneseT1Points());
        setTextView(rank2, eventModel.getJapaneseT2Rank());
        setTextView(points2, eventModel.getJapaneseT2Points());
        setTextView(notes, eventModel.getNote());

        if (eventModel.getNote() == null || eventModel.getNote().equals("")) {
            notes.setVisibility(View.GONE);
        }
    }

    private void setEventCardGrid() {
        if (nCardModel != null && srCardModel != null) {
            CardModel nCardRoundImage = new CardModel();
            nCardRoundImage.setRoundCardIdolizedImage(nCardModel.getRoundCardImage());
            CardModel srCardRoundImage = new CardModel();
            srCardRoundImage.setRoundCardIdolizedImage(srCardModel.getRoundCardImage());

            eventCardList.add(nCardRoundImage);
            eventCardList.add(nCardModel);
            eventCardList.add(srCardRoundImage);
            eventCardList.add(srCardModel);

            gridView.setAdapter(new GridCardListAdapter(EventDetailActivity.this, eventCardList, true));
            setGridViewItemClickListener();
        }
    }

    private void setGridViewItemClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventDetailActivity.this, CardDetailActivity.class);
                CardModel cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position);
                if (cardModel.getCardId() == null) {
                    cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position + 1);
                }
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });
    }

    private void readDataFromParentIntent() {
        eventModel = (EventModel) getIntent().getSerializableExtra("EventModel");
        nCardModel = (CardModel) getIntent().getSerializableExtra("nCardModel");
        srCardModel = (CardModel) getIntent().getSerializableExtra("srCardModel");
        eventCardList = new ArrayList<>();
    }

    public static void setTextView(TextView textView, Object object) {
        if (object != null && !object.equals("")) {
            String value = object.getClass().equals(Integer.class) ? object.toString() : (String) object;
            textView.setText(value);
        }
    }
}
