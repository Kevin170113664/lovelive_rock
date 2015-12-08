package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.activity.FullScreenCardActivity;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.List;

public class MediumCardListAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardModelList;
    private boolean isPromo;

    public MediumCardListAdapter(Context context, List<CardModel> cardModelList) {
        this.context = context;
        this.cardModelList = cardModelList;
        this.isPromo = cardModelList.get(0).getCardImage() == null || cardModelList.get(0).isPromo();
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
            if (isPromo) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_card_list_item_promo, parent, false);
            } else {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medium_card_list_item_default, parent, false);
            }
            bindItemView(convertView, viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setItemView(position, viewHolder);

        return convertView;
    }

    private void setItemView(int position, ViewHolder viewHolder) {
        if (!isPromo) {
            Picasso.with(context)
                    .load(cardModelList.get(position).getCardImage())
                    .into(viewHolder.mediumCardImage);
        }
        Picasso.with(context)
                .load(cardModelList.get(position).getCardIdolizedImage())
                .into(viewHolder.mediumCardIdolizedImage);
        setImageClickEvent(viewHolder);
        setSkillType(viewHolder.mediumCardSkillType, cardModelList.get(position).getSkill());

        setTextView(viewHolder.mediumCardIdolName, cardModelList.get(position).getJapaneseName());
        setTextView(viewHolder.mediumCardId, cardModelList.get(position).getCardId());
        setTextView(viewHolder.mediumCardMinSmile, cardModelList.get(position).getMinimumStatisticsSmile());
        setTextView(viewHolder.mediumCardMinPure, cardModelList.get(position).getMinimumStatisticsPure());
        setTextView(viewHolder.mediumCardMinCool, cardModelList.get(position).getMinimumStatisticsCool());
        setTextView(viewHolder.mediumCardNonIdolizedMaxSmile, cardModelList.get(position).getNonIdolizedMaximumStatisticsSmile());
        setTextView(viewHolder.mediumCardNonIdolizedMaxPure, cardModelList.get(position).getNonIdolizedMaximumStatisticsPure());
        setTextView(viewHolder.mediumCardNonIdolizedMaxCool, cardModelList.get(position).getNonIdolizedMaximumStatisticsCool());
        setTextView(viewHolder.mediumCardIdolizedMaxSmile, cardModelList.get(position).getIdolizedMaximumStatisticsSmile());
        setTextView(viewHolder.mediumCardIdolizedMaxPure, cardModelList.get(position).getIdolizedMaximumStatisticsPure());
        setTextView(viewHolder.mediumCardIdolizedMaxCool, cardModelList.get(position).getIdolizedMaximumStatisticsCool());
        setTextView(viewHolder.mediumCardReleaseDate, cardModelList.get(position).getReleaseDate());
        setTextView(viewHolder.mediumCardCenterSkill, cardModelList.get(position).getJapaneseCenterSkill());
        setTextView(viewHolder.mediumCardCenterSkillDetail, cardModelList.get(position).getJapaneseCenterSkillDetails());
        setTextView(viewHolder.mediumCardSkill, cardModelList.get(position).getJapaneseSkill());

        if (isPromo || isJapaneseSkillDetailEmpty(position)) {
            setTextView(viewHolder.mediumCardSkillDetail, cardModelList.get(position).getSkillDetails());
        } else {
            setTextView(viewHolder.mediumCardSkillDetail, cardModelList.get(position).getJapaneseSkillDetails());
        }
    }

    private boolean isJapaneseSkillDetailEmpty(int position) {
        return cardModelList.get(position).getJapaneseSkillDetails() == null || cardModelList.get(position).getJapaneseSkillDetails().equals("");
    }

    public static void setTextView(TextView textView, String value) {
        if (value != null && !value.equals("")) {
            textView.setText(value);
        }
    }

    public static void setSkillType(TextView textView, String value) {
        if (textView == null || value == null) {
            return;
        }
        switch (value) {
            case "Healer":
                textView.setText(R.string.skill_healer);
                break;
            case "Perfect Lock":
                textView.setText(R.string.skill_perfect_lock);
                break;
            case "Score Up":
                textView.setText(R.string.skill_score_up);
                break;
            case "Total Yell":
                textView.setText(R.string.skill_total_yell);
                break;
            case "Total Trick":
                textView.setText(R.string.skill_total_trick);
                break;
            case "Total Charm":
                textView.setText(R.string.skill_total_charm);
                break;
            case "Timer Yell":
                textView.setText(R.string.skill_timer_yell);
                break;
            case "Timer Trick":
                textView.setText(R.string.skill_timer_trick);
                break;
            case "Timer Charm":
                textView.setText(R.string.skill_timer_charm);
                break;
            case "Rhythmical Yell":
                textView.setText(R.string.skill_rhythmical_yell);
                break;
            case "Rhythmical Charm":
                textView.setText(R.string.skill_rhythmical_charm);
                break;
            case "Perfect Yell":
                textView.setText(R.string.skill_perfect_yell);
                break;
            case "Perfect Charm":
                textView.setText(R.string.skill_perfect_charm);
                break;
            default:
                break;
        }
    }

    private void setImageClickEvent(ViewHolder viewHolder) {
        viewHolder.mediumCardIdolizedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFullScreenActivity();
            }
        });

        if (!isPromo) {
            viewHolder.mediumCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createFullScreenActivity();
                }
            });
        }
    }

    private void createFullScreenActivity() {
        Intent intent = new Intent(context, FullScreenCardActivity.class);
        intent.putExtra("CardImage", cardModelList.get(0).getCardImage());
        intent.putExtra("IdolizedCardImage", cardModelList.get(0).getCardIdolizedImage());
        context.startActivity(intent);
    }

    private void bindItemView(View convertView, ViewHolder viewHolder) {
        if (!isPromo) {
            viewHolder.mediumCardImage = (ImageView) convertView.findViewById(R.id.medium_card_image);
        }
        viewHolder.mediumCardIdolizedImage = (ImageView) convertView.findViewById(R.id.medium_card_idolized_image);
        viewHolder.mediumCardIdolName = (TextView) convertView.findViewById(R.id.medium_card_idol_name);
        viewHolder.mediumCardId = (TextView) convertView.findViewById(R.id.medium_card_id);
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
        public ImageView mediumCardIdolizedImage;
        public TextView mediumCardIdolName;
        public TextView mediumCardId;
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
