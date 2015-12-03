package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import java.util.List;

public class EventListAdapter extends BaseAdapter {

    private Context context;
    private List<EventModel> eventModelList;

    public EventListAdapter(Context context, List<EventModel> eventModelList) {
        this.context = context;
        this.eventModelList = eventModelList;
    }

    @Override
    public int getCount() {
        return eventModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventModelList == null ? null : eventModelList;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.event_image);
            convertView.setTag(imageView);
        } else {
            imageView = (ImageView) convertView.getTag();
        }

        Picasso.with(context)
                .load(eventModelList.get(position).getEnglishImage())
                .into(imageView);

        return convertView;
    }
}
