package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.factory.CalculatorFactory;
import com.thoughtworks.lhli.lovelive_rock.model.point.Pt;

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

    @Bind(R.id.event_difficulty_spinner)
    protected Spinner eventDifficultySpinner;

    @Bind(R.id.event_song_rank_spinner)
    protected Spinner eventSongRankSpinner;

    @Bind(R.id.event_song_combo_spinner)
    protected Spinner eventSongComboSpinner;

    @Bind(R.id.normal_difficulty_spinner)
    protected Spinner normalDifficultySpinner;

    @Bind(R.id.event_end_day_text)
    protected EditText eventEndDayText;

    @Bind(R.id.event_end_hour_text)
    protected EditText eventEndHourText;

    @Bind(R.id.event_last_time_text)
    protected EditText eventLastTimeText;

    @Bind(R.id.calculate_button)
    protected Button calculateButton;

    @Bind(R.id.consume_lp_text)
    protected TextView consumeLpText;

    @Bind(R.id.pt_within_once_play_text)
    protected TextView ptWithinOncePlayText;

    @Bind(R.id.current_item_text)
    protected TextView currentItemText;

    private HashMap<String, String> consumeLpMap = new HashMap<>();
    private Pt pt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_calculator);
        ButterKnife.bind(this);
        initialiseActivityData();
    }

    private void initialiseActivityData() {
        setConsumeLpMap();
        setEventPointObject();
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
                        wastedLpEveryDayText.getText().toString(), currentLpText.getText().toString(),
                        currentExperienceText.getText().toString(), eventEndDayText.getText().toString(),
                        eventLastTimeText.getText().toString(), currentItemText.getText().toString(),
                        eventDifficultySpinner.getSelectedItem().toString(), eventSongRankSpinner.getSelectedItem().toString(),
                        eventSongComboSpinner.getSelectedItem().toString(), ptWithinOncePlayText.getText().toString(),
                        consumeLpText.getText().toString());

                Bundle calculationReport = new Bundle();
                setReportFields(calculatorFactory, calculationReport);

                DialogFragment normalReportDialogFragment = new ReportDialogFragment();
                normalReportDialogFragment.setArguments(calculationReport);
                normalReportDialogFragment.show(getFragmentManager(), "dialog");
            }

            private void setReportFields(CalculatorFactory calculatorFactory, Bundle calculationReport) {
                calculatorFactory.calculateNormalProcess();
                calculationReport.putString("event_type", "normal");
                calculationReport.putString("necessary_loveca", String.format("%s", calculatorFactory.getLovecaAmount()));
                calculationReport.putString("final_points", String.format("%s", calculatorFactory.getFinalPoints()));
                calculationReport.putString("final_rank", String.format("%s", calculatorFactory.getFinalRank()));
                calculationReport.putString("final_experience", String.format("%s/%s",
                        calculatorFactory.getFinalExperience(), calculatorFactory.getFinalRankUpExp()));
                calculationReport.putString("final_lp", String.format("%s", calculatorFactory.getFinalLp()));
                calculationReport.putString("final_item", String.format("%s", calculatorFactory.getFinalItem()));
                calculationReport.putString("play_frequency", String.format("%s", calculatorFactory.getTimesNeedToPlay()));
                calculationReport.putString("event_frequency", String.format("%s", calculatorFactory.getEventTimesNeedToPlay()));
                calculationReport.putString("total_time", calculatorFactory.getTotalPlayTime());
                calculationReport.putString("play_time_ratio", calculatorFactory.getPlayTimeRatio());
            }
        });
    }

    private void setConsumeLpMap() {
        consumeLpMap.put("Expert", "25");
        consumeLpMap.put("Hard", "15");
        consumeLpMap.put("Normal", "10");
        consumeLpMap.put("Easy", "5");
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
            System.out.print("initialise normal event basic points object failed.");
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

    private void setSpinnerSelectedListener() {
        eventDifficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        eventSongRankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        eventSongComboSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOncePoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        normalDifficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                consumeLpText.setText(consumeLpMap.get(normalDifficultySpinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        consumeLpText.setText("25");
    }

    protected void setOncePoints() {
        ptWithinOncePlayText.setText(String.format("%s", calculateOncePoints()));
    }

    @NonNull
    private Integer calculateOncePoints() {
        Boolean isFourMultiply = false;
        String rank = eventSongRankSpinner.getSelectedItem().toString();
        String combo = eventSongComboSpinner.getSelectedItem().toString();
        String difficulty = eventDifficultySpinner.getSelectedItem().toString();

        if (difficulty.substring(0, 1).equals("4")) {
            isFourMultiply = true;
            difficulty = difficulty.substring(2);
        }

        Integer points = pt.getPoints(difficulty, rank, combo);
        return isFourMultiply ? points * 4 : points;
    }

    private void setDropdownList() {
        setSpinnerAdapter(eventDifficultySpinner, R.array.normal_difficulty);
        setSpinnerAdapter(eventSongRankSpinner, R.array.song_rank);
        setSpinnerAdapter(eventSongComboSpinner, R.array.combo_rank);
        setSpinnerAdapter(normalDifficultySpinner, R.array.difficulty);

        eventSongComboSpinner.setSelection(1);
    }

    private void setSpinnerAdapter(Spinner spinner, int resourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, resourceId,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    protected void setEventTimeFields() {
        CalculatorFactory calculatorFactory = new CalculatorFactory(readLatestEventEndTime());
        eventEndDayText.setText(calculatorFactory.getEventEndDay());
        eventEndHourText.setText(calculatorFactory.getEventEndHour());
        eventLastTimeText.setText(calculatorFactory.getEventLastTime());
    }

    protected String readLatestEventEndTime() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("latestEventEndTime", null);
    }
}
