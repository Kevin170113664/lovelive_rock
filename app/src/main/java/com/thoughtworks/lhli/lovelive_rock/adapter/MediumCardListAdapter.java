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

import java.util.List;

public class MediumCardListAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardModelList;

    public MediumCardListAdapter(Context context, List<CardModel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_card_list_item, parent, false);
            bindItemView(convertView, viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItemView(position, viewHolder);

        return convertView;
    }

    private void setItemView(int position, ViewHolder viewHolder) {
        Picasso.with(context)
               .load(cardModelList.get(position).getCardIdolizedImage())
               .into(viewHolder.mediumCardImage);
        viewHolder.mediumCardIdolName.setText(cardModelList.get(position).getJapaneseName());
        viewHolder.mediumCardMinSmile.setText(cardModelList.get(position).getMinimumStatisticsSmile());
        viewHolder.mediumCardMinPure.setText(cardModelList.get(position).getMinimumStatisticsPure());
        viewHolder.mediumCardMinCool.setText(cardModelList.get(position).getMinimumStatisticsCool());
        viewHolder.mediumCardNonIdolizedMaxSmile.setText(cardModelList.get(position).getNonIdolizedMaximumStatisticsSmile());
        viewHolder.mediumCardNonIdolizedMaxPure.setText(cardModelList.get(position).getNonIdolizedMaximumStatisticsPure());
        viewHolder.mediumCardNonIdolizedMaxCool.setText(cardModelList.get(position).getNonIdolizedMaximumStatisticsCool());
        viewHolder.mediumCardIdolizedMaxSmile.setText(cardModelList.get(position).getIdolizedMaximumStatisticsSmile());
        viewHolder.mediumCardIdolizedMaxPure.setText(cardModelList.get(position).getIdolizedMaximumStatisticsPure());
        viewHolder.mediumCardIdolizedMaxCool.setText(cardModelList.get(position).getIdolizedMaximumStatisticsCool());
        viewHolder.mediumCardSkillType.setText(cardModelList.get(position).getSkill());
        viewHolder.mediumCardReleaseDate.setText(cardModelList.get(position).getReleaseDate());
        viewHolder.mediumCardCenterSkill.setText(cardModelList.get(position).getJapaneseCenterSkill());
        viewHolder.mediumCardCenterSkill.setText(cardModelList.get(position).getJapaneseCenterSkill());
        viewHolder.mediumCardCenterSkillDetail.setText(cardModelList.get(position).getJapaneseCenterSkillDetails());
        viewHolder.mediumCardSkill.setText(cardModelList.get(position).getJapaneseSkill());
        viewHolder.mediumCardSkillDetail.setText(cardModelList.get(position).getJapaneseSkillDetails());
    }

    private void bindItemView(View convertView, ViewHolder viewHolder) {
        viewHolder.mediumCardImage = (ImageView) convertView.findViewById(R.id.medium_card_image);
        viewHolder.mediumCardIdolName = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
        viewHolder.mediumCardMinSmile = (TextView) convertView.findViewById(R.id.medium_card_min_smile);
        viewHolder.mediumCardMinPure = (TextView) convertView.findViewById(R.id.medium_card_min_pure);
        viewHolder.mediumCardMinCool = (TextView) convertView.findViewById(R.id.medium_card_min_cool);
        viewHolder.mediumCardNonIdolizedMaxSmile = (TextView) convertView.findViewById(R.id.medium_card_non_idolized_max_smile);
        viewHolder.mediumCardNonIdolizedMaxPure = (TextView) convertView.findViewById(R.id.medium_card_non_idolized_max_pure);
        viewHolder.mediumCardNonIdolizedMaxCool = (TextView) convertView.findViewById(R.id.medium_card_non_idolized_max_cool);
        viewHolder.mediumCardIdolizedMaxSmile = (TextView) convertView.findViewById(R.id.medium_card_idolized_max_smile);
        viewHolder.mediumCardIdolizedMaxPure = (TextView) convertView.findViewById(R.id.medium_card_idolized_max_pure);
        viewHolder.mediumCardIdolizedMaxCool = (TextView) convertView.findViewById(R.id.medium_card_idolized_max_cool);
        viewHolder.mediumCardSkillType = (TextView) convertView.findViewById(R.id.medium_card_center_skill_type);
        viewHolder.mediumCardReleaseDate = (TextView) convertView.findViewById(R.id.medium_card_release_date);
        viewHolder.mediumCardCenterSkill = (TextView) convertView.findViewById(R.id.medium_card_center_skill);
        viewHolder.mediumCardCenterSkillDetail = (TextView) convertView.findViewById(R.id.medium_card_center_skill_detail);
        viewHolder.mediumCardSkill = (TextView) convertView.findViewById(R.id.medium_card_skill);
        viewHolder.mediumCardSkillDetail = (TextView) convertView.findViewById(R.id.medium_card_skill_detail);
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
        public TextView mediumCardSkillType;
        public TextView mediumCardReleaseDate;
        public TextView mediumCardCenterSkill;
        public TextView mediumCardCenterSkillDetail;
        public TextView mediumCardSkill;
        public TextView mediumCardSkillDetail;
    }
}
