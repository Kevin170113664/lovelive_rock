package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.CustomSeekBar;
import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
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

    @Bind(R.id.difficulty_seek_bar)
    protected CustomSeekBar difficultySeekBar;

    @Bind(R.id.random_difficulty_seek_bar)
    protected CustomSeekBar randomDifficultySeekBar;

    @Bind(R.id.notes_seek_bar)
    protected CustomSeekBar notesSeekBar;

    @OnClick(R.id.easy)
    protected void songEasyButtonEvent() {
        initSeekBar(songModel.getEasyDifficulty(), songModel.getEasyNotes());
    }

    @OnClick(R.id.normal)
    protected void songNormalButtonEvent() {
        initSeekBar(songModel.getNormalDifficulty(), songModel.getNormalNotes());
    }

    @OnClick(R.id.hard)
    protected void songHardButtonEvent() {
        initSeekBar(songModel.getHardDifficulty(), songModel.getHardNotes());
    }

    @OnClick(R.id.expert)
    protected void songExpertButtonEvent() {
        initSeekBar(songModel.getExpertDifficulty(), songModel.getExpertNotes());
        initRandomSeekBar();
    }

    private SongModel songModel;
    private final short SONG_MAX_DIFFICULTY = 12;
    private final short SONG_MAX_NOTES = 700;

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
        songExpertButtonEvent();
    }

    private void setSongSummary() {
        setSongAttributeIcon();
        songName.setText(songModel.getName());
        BPMValue.setText(songModel.getBPM().toString());
        durationValue.setText(songModel.getTime().toString());

        if (songModel.getDailyRotation() == null || songModel.getDailyRotation().equals("")) {
            dailyRotation.setVisibility(View.GONE);
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

    private void initSingleSeekBar(CustomSeekBar seekBar, Short progress, Short maxProgress) {
        seekBar.setMax(maxProgress);
        seekBar.setProgress(LoveLiveApp.getValidShort(progress));
        seekBar.setText(LoveLiveApp.getValidShort(progress).toString());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void initRandomSeekBar() {
        if (LoveLiveApp.getValidShort(songModel.getExpertRandomDifficulty()) != 0) {
            randomDifficultySeekBar.setVisibility(View.VISIBLE);
            initSingleSeekBar(randomDifficultySeekBar, songModel.getExpertRandomDifficulty(), SONG_MAX_NOTES);
        }
    }

    private void initSeekBar(Short normalDifficulty, Short normalNotes) {
        initSingleSeekBar(difficultySeekBar, normalDifficulty, SONG_MAX_DIFFICULTY);
        initSingleSeekBar(notesSeekBar, normalNotes, SONG_MAX_NOTES);
    }
}
