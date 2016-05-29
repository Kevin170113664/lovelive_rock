package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.activity.SongDetailActivity;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.util.ArrayList;
import java.util.List;

public class SongPagerAdapter extends PagerAdapter {

    private Activity activity;
    private List<SongModel> songModelList;
    private GridView songGridView;

    public SongPagerAdapter(Activity activity, List<SongModel> songModelList) {
        this.activity = activity;
        this.songModelList = songModelList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return LoveLiveApp.getInstance().getString(R.string.smileAttribute);
            case 1:
                return LoveLiveApp.getInstance().getString(R.string.pureAttribute);
            case 2:
                return LoveLiveApp.getInstance().getString(R.string.coolAttribute);
            default:
                return LoveLiveApp.getInstance().getString(R.string.default_text);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.song_grid_view, container, false);
        songGridView = (GridView) viewLayout.findViewById(R.id.song_grid_view);
        songGridView.setAdapter(new GridSongListAdapter(activity, filterSongsByPosition(position)));
        setGridViewClickListener();

        container.addView(viewLayout);
        return viewLayout;
    }

    private List<SongModel> filterSongsByPosition(int position) {
        SparseArray<String> attributes = new SparseArray<>();
        attributes.put(0, LoveLiveApp.getInstance().getString(R.string.smileAttribute));
        attributes.put(1, LoveLiveApp.getInstance().getString(R.string.pureAttribute));
        attributes.put(2, LoveLiveApp.getInstance().getString(R.string.coolAttribute));

        List<SongModel> songModelByPosition = new ArrayList<>();
        for (SongModel s : songModelList) {
            if (s.getAttribute().toLowerCase().equals(attributes.get(position).toLowerCase())) {
                songModelByPosition.add(s);
            }
        }
        return songModelByPosition;
    }

    private void setGridViewClickListener() {
        songGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, SongDetailActivity.class);
                SongModel songModel = ((List<SongModel>) parent.getAdapter().getItem(0)).get(position);
                intent.putExtra("SongModel", songModel);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
