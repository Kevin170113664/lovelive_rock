package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReportDialogFragment extends DialogFragment {

    @Bind(R.id.necessary_loveca_text)
    protected TextView necessaryLovecaText;

    @Bind(R.id.final_points_text)
    protected TextView finalPointsText;

    @Bind(R.id.final_rank_text)
    protected TextView finalRankText;

    @Bind(R.id.final_experience_text)
    protected TextView finalExperienceText;

    @Bind(R.id.final_lp_text)
    protected TextView finalLpText;

    @Bind(R.id.play_frequency_text)
    protected TextView playFrequencyText;

    @Bind(R.id.total_time_text)
    protected TextView totalTimeText;

    @Bind(R.id.play_time_ratio_text)
    protected TextView playTimeRatioText;

    @Bind(R.id.final_item_text)
    protected TextView finalItemText;

    @Bind(R.id.event_play_frequency_text)
    protected TextView eventPlayFrequencyText;

    @Bind(R.id.item_report)
    protected LinearLayout itemReport;

    @Bind(R.id.event_frequency_report)
    protected LinearLayout eventFrequencyReport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(R.string.result_report);

        Window window = getDialog().getWindow();
        window.setGravity(Gravity.TOP | Gravity.LEFT);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 150;
        window.setAttributes(params);

        return inflater.inflate(R.layout.dialog_calculation_report, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());

        switch (getArguments().getString("event_type")) {
            case "mf":
                setMfReport();
                break;
            case "normal":
                setNormalReport();
                break;
        }
    }

    protected void setMfReport() {
        necessaryLovecaText.setText(getArguments().getString("necessary_loveca"));
        finalPointsText.setText(getArguments().getString("final_points"));
        finalRankText.setText(getArguments().getString("final_rank"));
        finalExperienceText.setText(getArguments().getString("final_experience"));
        finalLpText.setText(getArguments().getString("final_lp"));
        playFrequencyText.setText(getArguments().getString("play_frequency"));
        totalTimeText.setText(getArguments().getString("total_time"));
        playTimeRatioText.setText(getArguments().getString("play_time_ratio"));
        itemReport.setVisibility(View.GONE);
        eventFrequencyReport.setVisibility(View.GONE);
    }

    protected void setNormalReport() {
        necessaryLovecaText.setText(getArguments().getString("necessary_loveca"));
        finalPointsText.setText(getArguments().getString("final_points"));
        finalRankText.setText(getArguments().getString("final_rank"));
        finalExperienceText.setText(getArguments().getString("final_experience"));
        finalLpText.setText(getArguments().getString("final_lp"));
        finalItemText.setText(getArguments().getString("final_item"));
        playFrequencyText.setText(getArguments().getString("play_frequency"));
        eventPlayFrequencyText.setText(getArguments().getString("event_frequency"));
        totalTimeText.setText(getArguments().getString("total_time"));
        playTimeRatioText.setText(getArguments().getString("play_time_ratio"));
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
