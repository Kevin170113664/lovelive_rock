package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.R;


public class SmallCardListAdapter extends BaseAdapter {

    private String data;

    public SmallCardListAdapter(String data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public String getItem(int position) {
        return data == null ? null : data;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_card_list_item, parent, false);
            viewHolder.smallCardImage = (ImageView) convertView.findViewById(R.id.small_card_image);
            viewHolder.smallCardCollection = (TextView) convertView.findViewById(R.id.small_card_collection);
            viewHolder.smallCardMaxStatistics = (TextView) convertView.findViewById(R.id.small_card_max_statistics);
            viewHolder.smallCardSkill = (TextView) convertView.findViewById(R.id.small_card_skill);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.smallCardCollection.setText("HHHHHHHHHHHHH");
        return convertView;
    }

    public class ViewHolder {
        public ImageView smallCardImage;
        public TextView smallCardCollection;
        public TextView smallCardMaxStatistics;
        public TextView smallCardSkill;
    }
}
