package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.factory.CalculatorFactory;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MedleyFestivalCalculatorActivity extends BaseActivity {

    @Bind(R.id.objective_points_text)
    protected EditText objectivePointsText;

    @Bind(R.id.current_points_text)
    protected EditText currentPointsText;

    @Bind(R.id.current_rank_text)
    protected EditText currentRankText;

    @Bind(R.id.points_addition)
    protected CheckBox pointsAddition;

    @Bind(R.id.experience_addition)
    protected CheckBox experienceAddition;

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

    @Bind(R.id.song_amount_spinner)
    protected Spinner songAmountSpinner;

    @Bind(R.id.difficulty_spinner)
    protected Spinner difficultySpinner;

    @Bind(R.id.normal_difficulty_spinner)
    protected Spinner songRankSpinner;

    @Bind(R.id.combo_rank_spinner)
    protected Spinner comboRankSpinner;

    @Bind(R.id.consume_lp_text)
    protected TextView songRankAdditionRatio;

    @Bind(R.id.combo_rank_addition_ratio)
    protected TextView comboRankAdditionRatio;

    @Bind(R.id.event_end_day_text)
    protected EditText eventEndDayText;

    @Bind(R.id.event_end_hour_text)
    protected EditText eventEndHourText;

    @Bind(R.id.event_last_time_text)
    protected EditText eventLastTimeText;

    @Bind(R.id.calculate_button)
    protected Button calculateButton;

    private HashMap<String, String> songRankMap = new HashMap<>();
    private HashMap<String, String> comboRankMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mf_calculator);
        ButterKnife.bind(this);
        initialiseActivityData();
    }

    private void initialiseActivityData() {
        setRankMap();
        setDropdownList();
        setButtonOnClickListener();
        setSpinnerSelectedListener();
        setEventTimeFields();
        setCalculateButtonClickListener();
        advancedOptionsLayout.setVisibility(View.GONE);
        eventTimeLayout.setVisibility(View.GONE);
    }

    private void setCalculateButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorFactory calculatorFactory = new CalculatorFactory(objectivePointsText.getText().toString(),
                        currentPointsText.getText().toString(), currentRankText.getText().toString(),
                        songAmountSpinner.getSelectedItem().toString(), difficultySpinner.getSelectedItem().toString(),
                        wastedLpEveryDayText.getText().toString(), pointsAddition.isChecked(),
                        experienceAddition.isChecked(), songRankMap.get(songRankSpinner.getSelectedItem().toString()),
                        comboRankMap.get(comboRankSpinner.getSelectedItem().toString()), currentLpText.getText().toString(),
                        currentExperienceText.getText().toString(), eventEndDayText.getText().toString(),
                        eventEndHourText.getText().toString(), eventLastTimeText.getText().toString());

                Bundle calculationReport = new Bundle();
                setReportFields(calculatorFactory, calculationReport);

                DialogFragment calculationReportDialogFragment = new CalculationReportDialogFragment();
                calculationReportDialogFragment.setArguments(calculationReport);
                calculationReportDialogFragment.show(getFragmentManager(), "dialog");
            }

            private void setReportFields(CalculatorFactory calculatorFactory, Bundle calculationReport) {
                calculatorFactory.calculateEventProcess();
                calculationReport.putString("necessary_loveca", String.format("%s", calculatorFactory.getLovecaAmount()));
                calculationReport.putString("final_points", String.format("%s", calculatorFactory.getFinalPoints()));
                calculationReport.putString("final_rank", String.format("%s", calculatorFactory.getFinalRank()));
                calculationReport.putString("final_experience", String.format("%s/%s",
                        calculatorFactory.getFinalExperience(), calculatorFactory.getFinalRankUpExp()));
                calculationReport.putString("final_lp", String.format("%s", calculatorFactory.getFinalLp()));
                calculationReport.putString("play_frequency", String.format("%s", calculatorFactory.getTimesNeedToPlay()));
                calculationReport.putString("total_time", calculatorFactory.getTotalPlayTime());
                calculationReport.putString("play_time_ratio", calculatorFactory.getPlayTimeRatio());
            }
        });
    }

    protected String readLatestEventEndTime() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("latestEventEndTime", null);
    }

    protected void setEventTimeFields() {
        CalculatorFactory calculatorFactory = new CalculatorFactory(readLatestEventEndTime());
        eventEndDayText.setText(calculatorFactory.getEventEndDay());
        eventEndHourText.setText(calculatorFactory.getEventEndHour());
        eventLastTimeText.setText(calculatorFactory.getEventLastTime());
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

    private void setRankMap() {
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

        comboRankSpinner.setSelection(1);
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
