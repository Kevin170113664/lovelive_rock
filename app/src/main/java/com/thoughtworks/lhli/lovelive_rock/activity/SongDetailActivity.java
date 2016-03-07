package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SongDetailActivity extends BaseActivity {

    @Bind(R.id.song_image)
    protected ImageView songImage;

    @Bind(R.id.song_attribute_icon)
    protected ImageView songAttributeIcon;

    @Bind(R.id.song_name)
    protected TextView songName;

    @Bind(R.id.daily_rotation)
    protected Button dailyRotation;

    @Bind(R.id.BPM_value)
    protected TextView BPMValue;

    @Bind(R.id.duration_value)
    protected TextView durationValue;

    @Bind(R.id.notes_progress_bar)
    protected NumberProgressBar notesProgressBar;

    @Bind(R.id.difficulty_progress_bar)
    protected NumberProgressBar difficultyProgressBar;

    @OnClick(R.id.easy)
    protected void songEasyButtonEvent() {
    }

    @OnClick(R.id.normal)
    protected void songNormalButtonEvent() {
    }

    @OnClick(R.id.hard)
    protected void songHardButtonEvent() {
    }

    @OnClick(R.id.expert)
    protected void songExpertButtonEvent() {
    }

    private SongModel songModel;
    private final int SONG_MAX_DIFFICULTY = 12;
    private final int SONG_MAX_NOTES = 650;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        ButterKnife.bind(this);
        loadSongData();
    }

    private void loadSongData() {
        songModel = (SongModel) getIntent().getSerializableExtra("SongModel");

        Picasso.with(this)
                .load(songModel.getImage())
                .into(songImage);

        setSongSummary();
        setSongProgressBar();
    }

    private void setSongSummary() {
        setSongAttributeIcon();
        songName.setText(songModel.getName());
        BPMValue.setText(songModel.getBPM().toString());
        durationValue.setText(songModel.getTime().toString());

        if (songModel.getDailyRotation() == null || songModel.getDailyRotation().equals("")) {
            dailyRotation.setVisibility(View.INVISIBLE);
        }
    }

    private void setSongAttributeIcon() {
        switch (songModel.getAttribute()) {
            case "Smile":
                songAttributeIcon.setImageResource(R.mipmap.smile_icon);
                break;
            case "Pure":
                songAttributeIcon.setImageResource(R.mipmap.pure_icon);
                break;
            case "Cool":
                songAttributeIcon.setImageResource(R.mipmap.cool_icon);
                break;
            default:
                break;
        }
    }

    private void setSongProgressBar() {
        difficultyProgressBar.setMax(SONG_MAX_DIFFICULTY);
        if (songModel.getExpertDifficulty() != null) {
            difficultyProgressBar.setProgress(songModel.getExpertDifficulty());
        }
        notesProgressBar.setMax(SONG_MAX_NOTES);
        if (songModel.getExpertNotes() != null) {
            notesProgressBar.setProgress(songModel.getExpertNotes());
        }
    }
}
