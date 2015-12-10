package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MedleyFestivalCalculatorActivity extends BaseActivity {

    @Bind(R.id.advanced_options_button)
    Button advancedOptionsButton;

    @Bind(R.id.event_time_button)
    Button eventEndTimeButton;

    @Bind(R.id.advanced_options)
    RelativeLayout advancedOptionsLayout;

    @Bind(R.id.event_time)
    RelativeLayout eventTimeLayout;

    @Bind(R.id.song_amount_spinner)
    Spinner songAmountSpinner;

    @Bind(R.id.difficulty_spinner)
    Spinner difficultySpinner;

    @Bind(R.id.song_rank_spinner)
    Spinner songRankSpinner;

    @Bind(R.id.combo_rank_spinner)
    Spinner comboRankSpinner;

    @Bind(R.id.song_rank_addition_ratio)
    TextView songRankAdditionRatio;

    @Bind(R.id.combo_rank_addition_ratio)
    TextView comboRankAdditionRatio;

    @Bind(R.id.event_end_day_text)
    TextView eventEndDayText;

    @Bind(R.id.event_end_hour_text)
    TextView eventEndHourText;

    HashMap<String, String> songRankMap = new HashMap<>();
    HashMap<String, String> comboRankMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mf_calculator);
        ButterKnife.bind(this);

        advancedOptionsLayout.setVisibility(View.GONE);
        eventTimeLayout.setVisibility(View.GONE);

        initialiseRankMap();
        setDropdownList();
        setButtonOnClickListener();
        setSpinnerSelectedListener();
        setEventEndTime();
    }

    protected void setEventEndTime() {
        LocalDateTime japaneseDateTime = new LocalDateTime(DateTime.parse(LoveLiveApp.getInstance().getLatestEventEnd()));
        DateTime localDateTime = new LocalDateTime(japaneseDateTime).toDateTime(DateTimeZone.UTC);

        eventEndDayText.setText(String.format("%s", localDateTime.getDayOfMonth()));
        eventEndHourText.setText(String.format("%s", localDateTime.getHourOfDay() - 1));
    }

    private void setSpinnerSelectedListener() {
        songRankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                songRankAdditionRatio.setText(songRankMap.get(songRankSpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        comboRankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                comboRankAdditionRatio.setText(comboRankMap.get(comboRankSpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initialiseRankMap() {
        songRankMap.put("S", "1.2");
        songRankMap.put("A", "1.15");
        songRankMap.put("B", "1.1");
        songRankMap.put("C", "1.05");
        songRankMap.put("-", "1");

        comboRankMap.put("S", "1.08");
        comboRankMap.put("A", "1.06");
        comboRankMap.put("B", "1.04");
        comboRankMap.put("C", "1.02");
        comboRankMap.put("-", "1");
    }

    private void setDropdownList() {
        setSpinnerAdapter(songAmountSpinner, R.array.song_amount);
        setSpinnerAdapter(difficultySpinner, R.array.difficulty);
        setSpinnerAdapter(songRankSpinner, R.array.song_rank);
        setSpinnerAdapter(comboRankSpinner, R.array.combo_rank);
    }

    private void setSpinnerAdapter(Spinner spinner, int resourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, resourceId,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
