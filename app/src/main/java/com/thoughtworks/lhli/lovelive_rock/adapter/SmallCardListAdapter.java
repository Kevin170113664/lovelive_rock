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
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SmallCardListAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardModelList;
    private Boolean isIdolizedFace;

    public SmallCardListAdapter(Context context, List<CardModel> cardModelList, Boolean isIdolizedFace) {
        this.context = context;
        this.cardModelList = cardModelList;
        this.isIdolizedFace = isIdolizedFace;
    }

    @Override
    public int getCount() {
        return cardModelList.size();
    }

    @Override
    public List<CardModel> getItem(int position) {
        return cardModelList == null ? null : cardModelList;
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
        statistics.add(Integer.parseInt(cardModelList.get(position).getIdolizedMaximumStatisticsSmile()));
        statistics.add(Integer.parseInt(cardModelList.get(position).getIdolizedMaximumStatisticsPure()));
        statistics.add(Integer.parseInt(cardModelList.get(position).getIdolizedMaximumStatisticsCool()));

        setImageView(position, viewHolder);
        MediumCardListAdapter.setTextView(viewHolder.smallCardCollection, cardModelList.get(position).getJapaneseCollection());
        MediumCardListAdapter.setTextView(viewHolder.smallCardMaxStatistics, Collections.max(statistics).toString());
        MediumCardListAdapter.setSkillType(viewHolder.smallCardSkill, cardModelList.get(position).getSkill());
    }

    private void setImageView(int position, ViewHolder viewHolder) {
        String imageUrl = cardModelList.get(position).getRoundCardIdolizedImage();

        if (shouldShowNonIdolizedImage(position)) {
            imageUrl = cardModelList.get(position).getRoundCardImage();
        }

        Picasso.with(context)
                .load(imageUrl)
                .into(viewHolder.smallCardImage);
    }

    private boolean shouldShowNonIdolizedImage(int position) {
        return isStringValid(cardModelList.get(position).getRoundCardImage()) && !isIdolizedFace &&
                !cardModelList.get(position).isPromo() && !cardModelList.get(position).isSpecial();
    }

    private void bindItemView(View convertView, ViewHolder viewHolder) {
        viewHolder.smallCardImage = (ImageView) convertView.findViewById(R.id.small_card_image);
        viewHolder.smallCardCollection = (TextView) convertView.findViewById(R.id.small_card_collection);
        viewHolder.smallCardMaxStatistics = (TextView) convertView.findViewById(R.id.small_card_max_statistics);
        viewHolder.smallCardSkill = (TextView) convertView.findViewById(R.id.small_card_skill);
    }

    private class ViewHolder {
        ImageView smallCardImage;
        TextView smallCardCollection;
        TextView smallCardMaxStatistics;
        TextView smallCardSkill;
    }

    private Boolean isStringValid(String value) {
        return value != null && !value.equals("");
    }
}
