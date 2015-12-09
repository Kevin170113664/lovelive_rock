package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.thoughtworks.lhli.lovelive_rock.R;

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

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mf_calculator);
        ButterKnife.bind(this);

        advancedOptionsLayout.setVisibility(View.GONE);
        eventTimeLayout.setVisibility(View.GONE);

        setDropdownList();
        setButtonOnClickListener();
    }

    private void setDropdownList() {
        setSpinnerAdapter(songAmountSpinner, R.array.song_amount);
        setSpinnerAdapter(difficultySpinner, R.array.difficulty);
        setSpinnerAdapter(songRankSpinner, R.array.song_rank);
        setSpinnerAdapter(comboRankSpinner, R.array.combo_rank);
    }

    private void setSpinnerAdapter(Spinner spinner, int resourceId) {
        adapter = ArrayAdapter.createFromResource(this, resourceId, android.R.layout.simple_spinner_item);
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
