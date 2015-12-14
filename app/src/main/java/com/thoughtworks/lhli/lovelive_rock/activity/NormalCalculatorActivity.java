package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.Pt;

import java.io.InputStream;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NormalCalculatorActivity extends BaseActivity {

    @Bind(R.id.objective_points_text)
    protected EditText objectivePointsText;

    @Bind(R.id.current_points_text)
    protected EditText currentPointsText;

    @Bind(R.id.current_rank_text)
    protected EditText currentRankText;

    @Bind(R.id.wasted_lp_every_day_text)
    protected EditText wastedLpEveryDayText;

    @Bind(R.id.current_lp_text)
    protected EditText currentLpText;

    @Bind(R.id.current_experience_text)
    protected EditText currentExperienceText;

    @Bind(R.id.advanced_options_button)
    protected Button advancedOptionsButton;

    @Bind(R.id.event_time_button)
    protected Button eventEndTimeButton;

    @Bind(R.id.advanced_options)
    protected RelativeLayout advancedOptionsLayout;

    @Bind(R.id.event_time)
    protected RelativeLayout eventTimeLayout;

    @Bind(R.id.song_rank_spinner)
    protected Spinner songRankSpinner;

    @Bind(R.id.song_rank_addition_ratio)
    protected TextView songRankAdditionRatio;

    @Bind(R.id.event_end_day_text)
    protected EditText eventEndDayText;

    @Bind(R.id.event_end_hour_text)
    protected EditText eventEndHourText;

    @Bind(R.id.event_last_time_text)
    protected EditText eventLastTimeText;

    @Bind(R.id.calculate_button)
    protected Button calculateButton;

    private HashMap<String, String> eventSongRankMap = new HashMap<>();
    private HashMap<String, String> eventComboRankMap = new HashMap<>();
    private Pt pt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_calculator);
        ButterKnife.bind(this);
        initialiseActivityData();
    }

    private void initialiseActivityData() {
        setEventPointObject();
        setButtonOnClickListener();
        advancedOptionsLayout.setVisibility(View.GONE);
        eventTimeLayout.setVisibility(View.GONE);
    }

    private void setEventPointObject() {
        try {
            InputStream inputStream = getAssets().open("normal_event.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            pt = new Gson().fromJson(new String(buffer, "UTF-8"), Pt.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setButtonOnClickListener() {
        advancedOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (advancedOptionsLayout.getVisibility() == View.GONE) {
                    advancedOptionsLayout.setVisibility(View.VISIBLE);
                } else {
                    advancedOptionsLayout.setVisibility(View.GONE);
                }

                if (eventTimeLayout.getVisibility() == View.VISIBLE) {
                    eventTimeLayout.setVisibility(View.GONE);
                }
            }
        });

        eventEndTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventTimeLayout.getVisibility() == View.GONE) {
                    eventTimeLayout.setVisibility(View.VISIBLE);
                } else {
                    eventTimeLayout.setVisibility(View.GONE);
                }

                if (advancedOptionsLayout.getVisibility() == View.VISIBLE) {
                    advancedOptionsLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}
