package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;

import java.util.List;

public class GridCardListAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardModelList;
    private Boolean isIdolizedFace;

    public GridCardListAdapter(Context context, List<CardModel> cardModelList, Boolean isIdolizedFace) {
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
        ImageView imageView;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_card_list_item, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.grid_view_image);
            convertView.setTag(imageView);
        } else {
            imageView = (ImageView) convertView.getTag();
        }

        setImageView(position, imageView);
        return convertView;
    }

    protected void setImageView(int position, ImageView imageView) {
        String imageUrl = cardModelList.get(position).getRoundCardIdolizedImage();

        if (isStringValid(cardModelList.get(position).getRoundCardImage()) && !isIdolizedFace) {
            imageUrl = cardModelList.get(position).getRoundCardImage();
        }

        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    private Boolean isStringValid(String value) {
        return value != null && !value.equals("");
    }
}
