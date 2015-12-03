package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.event_image);
            viewHolder.eventNameText = (TextView) convertView.findViewById(R.id.event_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = eventModelList.get(position).getEnglishImage();
        if (imageUrl == null || imageUrl.equals("")) {
            imageUrl = eventModelList.get(position).getImage();
        }

        Picasso.with(context)
                .load(imageUrl)
                .into(viewHolder.imageView);
        viewHolder.eventNameText.setText(eventModelList.get(position).getJapaneseName());

        return convertView;
    }

    public class ViewHolder {
        ImageView imageView;
        TextView eventNameText;
    }
}
