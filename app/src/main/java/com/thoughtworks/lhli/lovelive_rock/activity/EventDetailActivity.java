package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

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

    private EventModel eventModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        setEventDetailData();
    }

    private void setEventDetailData() {
        eventModel = (EventModel) getIntent().getSerializableExtra("EventModel");

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

    public static void setTextView(TextView textView, Object object) {
        if (object != null && !object.equals("")) {
            String value = object.getClass().equals(Integer.class) ? object.toString() : (String) object;
            textView.setText(value);
        }
    }

}
