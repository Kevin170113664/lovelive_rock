package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.bus.LatestEventEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.MainCardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.latest_event_image)
    protected ImageView latestEventImage;

    @Bind(R.id.latest_event_Sr_image)
    protected ImageView srImage;

    @Bind(R.id.latest_event_idolized_Sr_image)
    protected ImageView srIdolizedImage;

    @Bind(R.id.card_navigator)
    protected ImageView cardNavigator;

    @Bind(R.id.mf_calculator_navigator)
    protected ImageView mfCalculatorNavigator;

    @Bind(R.id.sm_calculator_navigator)
    protected ImageView smCalculatorNavigator;

    @Bind(R.id.traditional_calculator_navigator)
    protected ImageView normalCalculatorNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        Glide.with(this).load(R.drawable.loading).asGif()
                .into((ImageView) findViewById(R.id.loading_icon));

        new Thread(new LoadActivityData(this)).start();
        setImageNavigatorClickListener();
    }

    private void setImageNavigatorClickListener() {
        cardNavigator.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardActivity.class));
            }
        });

        mfCalculatorNavigator.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MedleyFestivalCalculatorActivity.class));
            }
        });

        smCalculatorNavigator.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_function_not_complete),
                        Toast.LENGTH_SHORT).show();
            }
        });

        normalCalculatorNavigator.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NormalCalculatorActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void saveLatestEventEndTime(String eventEndTime) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putString("latestEventEndTime", eventEndTime);
        editor.apply();
    }

    public void onEventMainThread(LatestEventEvent latestEventEvent) throws IOException {
        loadingIcon.setVisibility(View.GONE);
        saveLatestEventEndTime(latestEventEvent.getEventModelList().get(0).getEnd());

        Picasso.with(this)
                .load(latestEventEvent.getEventModelList().get(0).getImage())
                .into(latestEventImage);

        setEventImageClickListener();
    }

    public void onEventMainThread(final MainCardEvent mainCardEvent) {
        loadingIcon.setVisibility(View.GONE);
        final CardModel cardModel = mainCardEvent.getCardModelList().get(0);

        Picasso.with(this)
                .load(cardModel.getCardImage())
                .into(srImage);

        Picasso.with(this)
                .load(cardModel.getCardIdolizedImage())
                .into(srIdolizedImage);

        setEventSrImageClickListener(cardModel);
    }

    private void setEventImageClickListener() {
        latestEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventActivity.class));
            }
        });
    }

    private void setEventSrImageClickListener(final CardModel cardModel) {
        srImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardDetailActivity.class);
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });

        srIdolizedImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardDetailActivity.class);
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_song:
                Toast.makeText(getApplicationContext(), getString(R.string.toast_function_not_complete),
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_card:
                startActivity(new Intent(this, CardActivity.class));
                return true;
            case R.id.action_event:
                startActivity(new Intent(this, EventActivity.class));
                return true;
            case R.id.action_mf_calculator:
                startActivity(new Intent(this, MedleyFestivalCalculatorActivity.class));
                return true;
            case R.id.action_normal_calculator:
                startActivity(new Intent(MainActivity.this, NormalCalculatorActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}