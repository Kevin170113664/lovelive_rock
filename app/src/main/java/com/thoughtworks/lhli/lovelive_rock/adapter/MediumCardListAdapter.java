package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.activity.FullScreenCardActivity;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.CardSideCenterSkill;
import com.thoughtworks.lhli.lovelive_rock.util.Util;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MediumCardListAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardModelList;
    private boolean isPromo;
    private String sideCenter;

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
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.medium_card_list_item_promo, parent, false);
            } else {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.medium_card_list_item_default, parent, false);
            }
            bindItemView(convertView, viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setURsSideCenter(position);
        setItemView(position, viewHolder);

        return convertView;
    }

    private void setURsSideCenter(int position) {
        try {
            InputStream inputStream = LoveLiveApp.getInstance().getAssets().open("ur_side_center_skill.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            Type collectionType = new TypeToken<Collection<CardSideCenterSkill>>(){}.getType();
            Collection<CardSideCenterSkill> cardSideCenterSkills = new Gson()
                    .fromJson(new String(buffer, "UTF-8"), collectionType);

            for(CardSideCenterSkill c : cardSideCenterSkills) {
                if (c.getId().equals(Integer.parseInt(cardModelList.get(position).getCardId()))
                        && Util.isStringValid(c.getSkill())) {
                    sideCenter = c.getSkill();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.print("read side center skill json failed.");
        }
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

        if (cardModelList.get(position).getJapaneseName() != null && !cardModelList.get(position).getJapaneseName().equals("")) {
            setTextView(viewHolder.mediumCardIdolName, cardModelList.get(position).getJapaneseName());
        } else if (cardModelList.get(position).getIdolModel() != null) {
            setTextView(viewHolder.mediumCardIdolName, cardModelList.get(position).getIdolModel().getJapaneseName());
        }
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

        if (Util.isStringValid(sideCenter)) {
            setTextView(viewHolder.mediumCardSideCenterSkill, sideCenter);
        } else {
            viewHolder.mediumCardSideCenterSkill.setVisibility(View.GONE);
        }

        if (isPromo || isJapaneseSkillDetailEmpty(position)) {
            setTextView(viewHolder.mediumCardSkillDetail, cardModelList.get(position).getSkillDetails());
        } else {
            setTextView(viewHolder.mediumCardSkillDetail, cardModelList.get(position).getJapaneseSkillDetails());
        }
    }

    private boolean isJapaneseSkillDetailEmpty(int position) {
        return cardModelList.get(position).getJapaneseSkillDetails() == null || cardModelList.get(position).getJapaneseSkillDetails().equals("");
    }

    static void setTextView(TextView textView, String value) {
        if (value != null && !value.equals("")) {
            textView.setText(value);
        } else {
            textView.setText(R.string.default_text);
        }
    }

    static void setSkillType(TextView textView, String value) {
        if (textView == null) {
            return;
        }
        if (value == null) {
            textView.setText(R.string.card_skill_default_text);
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
                textView.setText(R.string.card_skill_default_text);
                break;
        }
    }

    private void setImageClickEvent(ViewHolder viewHolder) {
        setIdolizedImageClickEvent(viewHolder);
        setNonIdolizedImageClickEvent(viewHolder);
    }

    private void setNonIdolizedImageClickEvent(ViewHolder viewHolder) {
        if (!isPromo) {
            viewHolder.mediumCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createFullScreenActivity();
                }
            });

            viewHolder.mediumCardImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    LoveLiveApp.file_download(cardModelList.get(0).getCardImage(), context);
                    return true;
                }
            });
        }
    }

    private void setIdolizedImageClickEvent(ViewHolder viewHolder) {
        viewHolder.mediumCardIdolizedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFullScreenActivity();
            }
        });

        viewHolder.mediumCardIdolizedImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LoveLiveApp.file_download(cardModelList.get(0).getCardIdolizedImage(), context);
                return true;
            }
        });
    }

    private void createFullScreenActivity() {
        Intent intent = new Intent(context, FullScreenCardActivity.class);
        ArrayList<String> images = new ArrayList<>();
        images.add(cardModelList.get(0).getCardImage());
        images.add(cardModelList.get(0).getCardIdolizedImage());
        images.add(cardModelList.get(0).getTransparentImage());
        images.add(cardModelList.get(0).getTransparentIdolizedImage());
        images.add(cardModelList.get(0).getCleanUr());
        images.add(cardModelList.get(0).getCleanUrIdolized());
        images.removeAll(Collections.singleton(null));

        intent.putStringArrayListExtra("images", images);
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
        viewHolder.mediumCardSideCenterSkill = (TextView) convertView.findViewById(R.id.medium_card_side_center_skill);
        viewHolder.mediumCardSkill = (TextView) convertView.findViewById(R.id.medium_card_skill);
        viewHolder.mediumCardSkillDetail = (TextView) convertView.findViewById(R.id.medium_card_skill_detail);
    }

    private class ViewHolder {
        ImageView mediumCardImage;
        ImageView mediumCardIdolizedImage;
        TextView mediumCardIdolName;
        TextView mediumCardId;
        TextView mediumCardMinSmile;
        TextView mediumCardMinPure;
        TextView mediumCardMinCool;
        TextView mediumCardNonIdolizedMaxSmile;
        TextView mediumCardNonIdolizedMaxPure;
        TextView mediumCardNonIdolizedMaxCool;
        TextView mediumCardIdolizedMaxSmile;
        TextView mediumCardIdolizedMaxPure;
        TextView mediumCardIdolizedMaxCool;
        TextView mediumCardSkillType;
        TextView mediumCardReleaseDate;
        TextView mediumCardCenterSkill;
        TextView mediumCardCenterSkillDetail;
        TextView mediumCardSideCenterSkill;
        TextView mediumCardSkill;
        TextView mediumCardSkillDetail;
    }
}
