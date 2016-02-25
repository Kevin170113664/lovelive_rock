package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.SongModel;

import java.util.List;

public class GridSongListAdapter extends BaseAdapter {

    private Context context;
    private List<SongModel> songModelList;

    public GridSongListAdapter(Context context, List<SongModel> songModelList) {
        this.context = context;
        this.songModelList = songModelList;
    }

    @Override
    public int getCount() {
        return songModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return songModelList == null ? null : songModelList;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_song_item, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.grid_view_image);
            convertView.setTag(imageView);
        } else {
            imageView = (ImageView) convertView.getTag();
        }

        Picasso.with(context)
                .load(songModelList.get(position).getImage())
                .into(imageView);
        return convertView;
    }
}
