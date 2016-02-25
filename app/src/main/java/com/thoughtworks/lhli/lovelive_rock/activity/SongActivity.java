package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.view.View;
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

import java.util.List;

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

        setContentView(R.layout.activity_card);
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
        gridView.setAdapter(new GridSongListAdapter(this, songModelList));
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
}
