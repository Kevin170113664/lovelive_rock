package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardDetailActivity extends AppCompatActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
//        listView.setAdapter(new MediumCardListAdapter("Start Dash!"));
    }
}
