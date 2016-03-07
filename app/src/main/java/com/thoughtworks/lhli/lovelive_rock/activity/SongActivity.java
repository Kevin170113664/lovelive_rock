package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridSongListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SongEvent;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class SongActivity extends BaseActivity {

    @Bind(R.id.grid_view)
    protected GridView gridView;

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.download_progress)
    protected TextView downloadProgress;

    @Bind(R.id.download_tips)
    protected TextView downloadTips;

    @Bind(R.id.progress_bar)
    protected ProgressBar progressBar;

    List<SongModel> songModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_song);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif().into(loadingIcon);

        new Thread(new LoadActivityData(this)).start();
    }

    @Override
    protected void onDestroy() {
        Thread.currentThread().interrupt();
        super.onDestroy();
    }

    public void onEventMainThread(SongEvent songEvent) {
        findViewById(R.id.loading_mask).setVisibility(View.GONE);
        loadingIcon.setVisibility(View.GONE);
        songModelList = songEvent.getSongModelList();
        loadSongView();
        setGridViewClickListener();
    }

    private void loadSongView() {
        removeDuplicateSong();
        Long seed = System.nanoTime();
        Collections.shuffle(songModelList, new Random(seed));
        gridView.setAdapter(new GridSongListAdapter(this, songModelList));
    }

    private void setGridViewClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SongActivity.this, SongDetailActivity.class);
                SongModel songModel = ((List<SongModel>) parent.getAdapter().getItem(0)).get(position);
                intent.putExtra("SongModel", songModel);
                startActivity(intent);
            }
        });
    }

    public void onEventMainThread(FetchProcessEvent fetchProcessEvent) {
        if (!fetchProcessEvent.getProcess().equals("100")) {
            downloadTips.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            downloadProgress.setVisibility(View.VISIBLE);
            progressBar.setProgress(Integer.parseInt(fetchProcessEvent.getProcess()));
            downloadProgress.setText(String.format("%s%%", fetchProcessEvent.getProcess()));
        }
    }

    private void removeDuplicateSong() {
        HashMap<String, SongModel> songMap = new HashMap<>();
        List<SongModel> tempSongModelList = new ArrayList<>();

        tempSongModelList.addAll(songModelList);
        songModelList.clear();

        for (SongModel s : tempSongModelList) {
            songMap.put(s.getName(), s);
        }

        for (SongModel s : songMap.values()) {
            songModelList.add(s);
        }
    }
}
