package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.SmallCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.SmallCardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class CardActivity extends BaseActivity {

    @Bind(R.id.small_card_list)
    protected ListView listView;

    @OnClick({R.id.filter_n, R.id.filter_r, R.id.filter_sr, R.id.filter_ur})
    public void filterCardByRarity(View view) {
        switch (((Button) view).getText().toString()) {
            case "N":
                filterCardByRarity("N");
                break;
            case "R":
                filterCardByRarity("R");
                break;
            case "SR":
                filterCardByRarity("SR");
                break;
            case "UR":
                filterCardByRarity("UR");
                break;
            default:
                break;
        }
    }

    private List<CardModel> cardModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif()
                .into((ImageView) findViewById(R.id.loading_icon));
        new LoadActivityData(this).execute();
    }

    public void onEventMainThread(SmallCardEvent smallCardEvent) {
        findViewById(R.id.loading_icon).setVisibility(View.GONE);
        ButterKnife.bind(this);
        cardModelList = smallCardEvent.getCardModelList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardActivity.this, CardDetailActivity.class);
                intent.putExtra("cardId", ((List<CardModel>) ((ListView) parent).getAdapter().getItem(0)).get(position).getCardId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterCardByRarity(String rarity) {
        List<CardModel> rarityCardModelList = new ArrayList<>();
        for (CardModel cardModel : cardModelList) {
            if (cardModel.getRarity().equals(rarity)) {
                rarityCardModelList.add(cardModel);
            }
        }
        listView.setAdapter(new SmallCardListAdapter(CardActivity.this, rarityCardModelList));
    }
}