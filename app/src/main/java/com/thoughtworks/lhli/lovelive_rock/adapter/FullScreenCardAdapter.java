package com.thoughtworks.lhli.lovelive_rock.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.thoughtworks.lhli.lovelive_rock.R;

import java.util.List;

public class FullScreenCardAdapter extends PagerAdapter {

    private Activity activity;
    private List<String> cardImageList;
    private LayoutInflater inflater;

    public FullScreenCardAdapter(Activity activity, List<String> cardImageList) {
        this.activity = activity;
        this.cardImageList = cardImageList;
    }

    @Override
    public int getCount() {
        return cardImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView fullScreenImage;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.full_screen_image, container, false);
        fullScreenImage = (ImageView) viewLayout.findViewById(R.id.full_screen_image);

        Picasso.with(activity)
                .load(cardImageList.get(position))
                .into(fullScreenImage);

        fullScreenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
