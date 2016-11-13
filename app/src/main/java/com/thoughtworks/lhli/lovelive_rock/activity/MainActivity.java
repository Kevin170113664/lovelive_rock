package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;
import com.umeng.update.UmengUpdateAgent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {
    private List<Integer> navResourceIds = new ArrayList<>();

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.latest_event_image)
    protected ImageView latestEventImage;

    @Bind(R.id.card_navigator)
    protected ImageView cardNavigator;

    @Bind(R.id.song_navigator)
    protected ImageView songNavigator;

    @Bind(R.id.cf_calculator_navigator)
    protected ImageView cfCalculatorNavigator;

    @Bind(R.id.sm_calculator_navigator)
    protected ImageView smCalculatorNavigator;

    @Bind(R.id.normal_calculator_navigator)
    protected ImageView normalCalculatorNavigator;

    @Bind(R.id.mf_calculator_navigator)
    protected ImageView mfCalculatorNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setToolBar();
        setUpCountConfig();
        Glide.with(this).load(R.drawable.loading).asGif().into((ImageView) findViewById(R.id.loading_icon));
        showCleanURsRandomly();

        new Thread(new LoadActivityData(this)).start();
    }

    private void showCleanURsRandomly() {
        navResourceIds.add(R.mipmap.nav_rincleanur666);
        navResourceIds.add(R.mipmap.nav_makicleanuridolized701);
        navResourceIds.add(R.mipmap.nav_hanayocleanuridolized556);
        navResourceIds.add(R.mipmap.nav_honokacleanuridolized980);
        navResourceIds.add(R.mipmap.nav_kotoricleanuridolized1002);
        navResourceIds.add(R.mipmap.nav_umicleanuridolized214);
        navResourceIds.add(R.mipmap.nav_elicleanuridolized659);
        navResourceIds.add(R.mipmap.nav_nicocleanuridolized909);
        navResourceIds.add(R.mipmap.nav_nozomicleanuridolized367);
        navResourceIds.add(R.mipmap.nav_hanamarucleanuridolized974);
        navResourceIds.add(R.mipmap.nav_rubycleanuridolized985);
        navResourceIds.add(R.mipmap.nav_yoshikocleanuridolized1018);
        navResourceIds.add(R.mipmap.nav_chikacleanur955);
        navResourceIds.add(R.mipmap.nav_rikocleanur1041);
        navResourceIds.add(R.mipmap.nav_youcleanur957);
        navResourceIds.add(R.mipmap.nav_diacleanuridolized1030);
        navResourceIds.add(R.mipmap.nav_kanancleanur1007);
        navResourceIds.add(R.mipmap.nav_maricleanur997);

        Collections.shuffle(navResourceIds);

        cardNavigator.setImageResource(navResourceIds.get(0));
        songNavigator.setImageResource(navResourceIds.get(1));
        cfCalculatorNavigator.setImageResource(navResourceIds.get(2));
        smCalculatorNavigator.setImageResource(navResourceIds.get(3));
        normalCalculatorNavigator.setImageResource(navResourceIds.get(4));
        mfCalculatorNavigator.setImageResource(navResourceIds.get(5));
    }

    private void setUpCountConfig() {
        UmengUpdateAgent.update(this);
    }

    private void setToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @OnClick(R.id.card_navigator)
    public void cardNavigatorEvent() {
        startActivity(new Intent(MainActivity.this, CardActivity.class));
    }

    @OnClick(R.id.mf_calculator_navigator)
    public void mfCalculatorEvent() {
        startActivity(new Intent(MainActivity.this, MedleyFestivalCalculatorActivity.class));
    }

    @OnClick(R.id.sm_calculator_navigator)
    public void smCalculatorEvent() {
        startActivity(new Intent(MainActivity.this, ScoreMatchCalculatorActivity.class));
    }

    @OnClick(R.id.normal_calculator_navigator)
    public void normalCalculatorEvent() {
        startActivity(new Intent(MainActivity.this, NormalCalculatorActivity.class));
    }

    @OnClick(R.id.song_navigator)
    public void songNavigatorEvent() {
        startActivity(new Intent(this, SongActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void saveLatestEventEndTime(String eventEndTime) {
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
    }

    private void setEventImageClickListener() {
        latestEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_song:
                startActivity(new Intent(this, SongActivity.class));
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
            case R.id.action_sm_calculator:
                startActivity(new Intent(this, ScoreMatchCalculatorActivity.class));
                return true;
            case R.id.action_normal_calculator:
                startActivity(new Intent(MainActivity.this, NormalCalculatorActivity.class));
                return true;
            case R.id.action_clear_data:
                truncateAllTables();
                Toast.makeText(getApplicationContext(), R.string.clear_app_data_successfully,
                        Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void truncateAllTables() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = openOrCreateDatabase("lovelive-db", MODE_PRIVATE, null);
                db.execSQL("DELETE FROM CARD;");
                db.execSQL("DELETE FROM EVENT;");
                db.execSQL("DELETE FROM IDOL;");
                db.execSQL("DELETE FROM CHARACTER_VOICE;");
                db.execSQL("DELETE FROM SONG;");
                db.close();
            }
        }).start();
    }
}