package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridCardListAdapter;
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

    @Bind(R.id.grid_view)
    protected GridView gridView;

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

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

    private boolean isGridView = false;

    private List<CardModel> cardModelList;
    private List<CardModel> visibleCardModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif().into(loadingIcon);
        new LoadActivityData(this).execute();
    }

    public void onEventMainThread(SmallCardEvent smallCardEvent) {
        findViewById(R.id.loading_mask).setVisibility(View.GONE);
        loadingIcon.setVisibility(View.GONE);
        cardModelList = smallCardEvent.getCardModelList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardActivity.this, CardDetailActivity.class);
                intent.putExtra("cardId", ((List<CardModel>) ((ListView) parent).getAdapter().getItem(0)).get(position).getCardId());
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardActivity.this, CardDetailActivity.class);
                intent.putExtra("cardId", ((List<CardModel>) ((GridView) parent).getAdapter().getItem(0)).get(position).getCardId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        isGridView = item.getItemId() != R.id.action_menu;
        invalidateOptionsMenu();
        return true;
    }

    private void filterCardByRarity(String rarity) {
        visibleCardModelList.clear();
        for (CardModel cardModel : cardModelList) {
            if (cardModel.getRarity().equals(rarity)) {
                visibleCardModelList.add(cardModel);
            }
        }
        loadCardView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card, menu);
        menu.findItem(R.id.action_grid).setVisible(!isGridView);
        menu.findItem(R.id.action_menu).setVisible(isGridView);
        loadCardView();
        return true;
    }

    private void loadCardView() {
        List<CardModel> emptyCardModelList = new ArrayList<>();
        if (isGridView) {
            listView.setAdapter(new SmallCardListAdapter(CardActivity.this, emptyCardModelList));
            gridView.setAdapter(new GridCardListAdapter(CardActivity.this, visibleCardModelList));
        } else {
            gridView.setAdapter(new GridCardListAdapter(CardActivity.this, emptyCardModelList));
            listView.setAdapter(new SmallCardListAdapter(CardActivity.this, visibleCardModelList));
        }
    }
}