package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.lhli.lovelive_rock.R;

public class MediumCardListAdapter extends BaseAdapter {

    private String data;

    public MediumCardListAdapter(String data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_card_list_item, parent, false);
            viewHolder.mediumCardImage = (ImageView) convertView.findViewById(R.id.medium_card_image);
            viewHolder.mediumCardIdolName = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardMinSmile = (TextView) convertView.findViewById(R.id.medium_card_min_smile);
            viewHolder.mediumCardMinPure = (TextView) convertView.findViewById(R.id.medium_card_min_pure);
            viewHolder.mediumCardMinCool = (TextView) convertView.findViewById(R.id.medium_card_min_cool);
            viewHolder.mediumCardNonIdolizedMaxSmile = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardNonIdolizedMaxPure = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardNonIdolizedMaxCool = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardIdolizedMaxSmile = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardIdolizedMaxPure = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardIdolizedMaxCool = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardCenterSkill = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
            viewHolder.mediumCardCenterSkill1 = (TextView) convertView.findViewById(R.id.medium_card_idol_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.mediumCardIdolName.setText("OOOOOOOOOOO");
        return convertView;
    }

    public class ViewHolder {
        public ImageView mediumCardImage;
        public TextView mediumCardIdolName;
        public TextView mediumCardMinSmile;
        public TextView mediumCardMinPure;
        public TextView mediumCardMinCool;
        public TextView mediumCardNonIdolizedMaxSmile;
        public TextView mediumCardNonIdolizedMaxPure;
        public TextView mediumCardNonIdolizedMaxCool;
        public TextView mediumCardIdolizedMaxSmile;
        public TextView mediumCardIdolizedMaxPure;
        public TextView mediumCardIdolizedMaxCool;
        public TextView mediumCardCenterSkill;
        public TextView mediumCardCenterSkill1;
    }
}
