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
import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SmallCardListAdapter extends BaseAdapter {

    private Context context;
    private List<Card> cardList;

    public SmallCardListAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public List<Card> getItem(int position) {
        return cardList == null ? null : cardList;
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
            bindItemView(convertView, viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItemView(position, viewHolder);

        return convertView;
    }

    private void setItemView(int position, ViewHolder viewHolder) {
        List<Integer> statistics = new ArrayList<>();
        statistics.add(Integer.parseInt(cardList.get(position).getIdolizedMaximumStatisticsSmile()));
        statistics.add(Integer.parseInt(cardList.get(position).getIdolizedMaximumStatisticsPure()));
        statistics.add(Integer.parseInt(cardList.get(position).getIdolizedMaximumStatisticsCool()));

        Picasso.with(context)
                .load(cardList.get(position).getRoundCardIdolizedImage())
                .into(viewHolder.smallCardImage);
        viewHolder.smallCardCollection.setText(cardList.get(position).getJapaneseCollection());
        viewHolder.smallCardMaxStatistics.setText(Collections.max(statistics).toString());
        viewHolder.smallCardSkill.setText(cardList.get(position).getSkill());
    }

    private void bindItemView(View convertView, ViewHolder viewHolder) {
        viewHolder.smallCardImage = (ImageView) convertView.findViewById(R.id.small_card_image);
        viewHolder.smallCardCollection = (TextView) convertView.findViewById(R.id.small_card_collection);
        viewHolder.smallCardMaxStatistics = (TextView) convertView.findViewById(R.id.small_card_max_statistics);
        viewHolder.smallCardSkill = (TextView) convertView.findViewById(R.id.small_card_skill);
    }

    public class ViewHolder {
        public ImageView smallCardImage;
        public TextView smallCardCollection;
        public TextView smallCardMaxStatistics;
        public TextView smallCardSkill;
    }
}
