package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.FullScreenCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class FullScreenCardActivity extends BaseActivity {

    private List<String> cardImageList = new ArrayList<>();
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_card);

        loadImage();
    }

    private void loadImage() {
        if (getIntent().getStringExtra("CardImage") != null) {
            cardImageList.add(getIntent().getStringExtra("CardImage"));
        }
        cardImageList.add(getIntent().getStringExtra("IdolizedCardImage"));
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FullScreenCardAdapter(this, cardImageList));
    }
}
