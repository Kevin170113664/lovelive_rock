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
import butterknife.OnTextChanged;

public class ScoreMatchCalculatorActivity extends BaseActivity {

    @Bind(R.id.objective_points_text)
    protected EditText objectivePointsText;

    @Bind(R.id.current_points_text)
    protected EditText currentPointsText;

    @Bind(R.id.current_rank_text)
    protected EditText currentRankText;

    @Bind(R.id.play_rank_spinner)
    protected Spinner playRankSpinner;

    @Bind(R.id.pt_within_once_play_text)
    protected TextView ptWithinOncePlayText;

    @Bind(R.id.wasted_lp_every_day_text)
    protected EditText wastedLpEveryDayText;

    @Bind(R.id.difficulty_spinner)
    protected Spinner difficultySpinner;

    @Bind(R.id.song_rank_spinner)
    protected Spinner songRankSpinner;

    @Bind(R.id.current_lp_text)
    protected EditText currentLpText;

    @Bind(R.id.current_experience_text)
    protected EditText currentExperienceText;

    @Bind(R.id.advanced_options_button)
    protected Button advancedOptionsButton;

    @Bind(R.id.event_time_button)
    protected Button eventTimeButton;

    @Bind(R.id.advanced_options)
    protected RelativeLayout advancedOptionsLayout;

    @Bind(R.id.event_time)
    protected RelativeLayout eventTimeLayout;

    @Bind(R.id.event_end_day_text)
    protected EditText eventEndDayText;

    @Bind(R.id.event_end_hour_text)
    protected EditText eventEndHourText;

    @Bind(R.id.event_last_time_text)
    protected EditText eventLastTimeText;

    @Bind(R.id.calculate_button)
    protected Button calculateButton;

    @Bind(R.id.country_exp_checkbox)
    protected CheckBox countryExpCheckbox;

    @OnTextChanged(R.id.event_end_day_text)
    public void calculateEventLastTimeWhenDayChanged() {
        updateEventLastTimeText();
    }

    @OnTextChanged(R.id.event_end_hour_text)
    public void calculateEventLastTimeWhenHourChanged() {
        updateEventLastTimeText();
    }

    HashMap<String, Long> difficultyBasicPointMap = new HashMap<>();
    HashMap<String, Double> playRankMap = new HashMap<>();
    HashMap<String, Double> songRankMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_calculator);
        ButterKnife.bind(this);
        initialiseActivityData();
    }

    private void initialiseActivityData() {
        setBasicMap();
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
                        playRankSpinner.getSelectedItem().toString(), ptWithinOncePlayText.getText().toString(),
                        wastedLpEveryDayText.getText().toString(), difficultySpinner.getSelectedItem().toString(),
                        songRankSpinner.getSelectedItem().toString(), currentLpText.getText().toString(),
                        currentExperienceText.getText().toString(), eventEndDayText.getText().toString(),
                        eventLastTimeText.getText().toString(), countryExpCheckbox.isChecked());

                Bundle calculationReport = new Bundle();
                setReportFields(calculatorFactory, calculationReport);

                DialogFragment smReportDialogFragment = new ReportDialogFragment();
                smReportDialogFragment.setArguments(calculationReport);
                smReportDialogFragment.show(getFragmentManager(), "dialog");
            }

            private void setReportFields(CalculatorFactory calculatorFactory, Bundle calculationReport) {
                calculatorFactory.calculateSmProcess();
                calculationReport.putString("event_type", "sm");
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

    private void setBasicMap() {
        difficultyBasicPointMap.put("Expert", 272L);
        difficultyBasicPointMap.put("Hard", 163L);
        difficultyBasicPointMap.put("Normal", 89L);
        difficultyBasicPointMap.put("Easy", 36L);

        playRankMap.put(getString(R.string.average), 1.1125);
        playRankMap.put(getString(R.string.first_place), 1.25);
        playRankMap.put(getString(R.string.second_place), 1.15);
        playRankMap.put(getString(R.string.third_place), 1.05);
        playRankMap.put(getString(R.string.forth_place), 1.00);

        songRankMap.put("S", 1.20);
        songRankMap.put("A", 1.15);
        songRankMap.put("B", 1.10);
        songRankMap.put("C", 1.05);
        songRankMap.put("-", 1.00);
    }

    protected void setOncePoints() {
        ptWithinOncePlayText.setText(String.format("%s", calculateOncePoints()));
    }

    private Long calculateOncePoints() {
        String playRank = playRankSpinner.getSelectedItem().toString();
        String difficulty = difficultySpinner.getSelectedItem().toString();
        String songRank = songRankSpinner.getSelectedItem().toString();

        return Math.round(difficultyBasicPointMap.get(difficulty) * playRankMap.get(playRank) * songRankMap.get(songRank));
    }


    private void setSpinnerSelectedListener() {
        playRankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        songRankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

        eventTimeButton.setOnClickListener(new View.OnClickListener() {
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

    protected String readLatestEventEndTime() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("latestEventEndTime", null);
    }

    protected void setEventTimeFields() {
        CalculatorFactory calculatorFactory = new CalculatorFactory(readLatestEventEndTime());
        eventEndDayText.setText(calculatorFactory.getEventEndDay());
        eventEndHourText.setText(calculatorFactory.getEventEndHour());
        eventLastTimeText.setText(calculatorFactory.getEventLastTime());
    }

    private void setSpinnerAdapter(Spinner spinner, int resourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, resourceId,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setDropdownList() {
        setSpinnerAdapter(playRankSpinner, R.array.play_rank);
        setSpinnerAdapter(difficultySpinner, R.array.difficulty);
        setSpinnerAdapter(songRankSpinner, R.array.song_rank);
    }

    protected void updateEventLastTimeText() {
        CalculatorFactory calculatorFactory = new CalculatorFactory();
        String eventLastTime = calculatorFactory.getEventLastTime(eventEndDayText.getText().toString(),
                eventEndHourText.getText().toString());
        eventLastTimeText.setText(eventLastTime);
    }
}
