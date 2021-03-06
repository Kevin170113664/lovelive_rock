package com.thoughtworks.lhli.lovelive_rock.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.adapter.SmallCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.FilterEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SmallCardEvent;
import com.thoughtworks.lhli.lovelive_rock.factory.FilterFactory;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CardActivity extends BaseActivity {

    @Bind(R.id.small_card_list)
    protected ListView listView;

    @Bind(R.id.grid_view)
    protected GridView gridView;

    @Bind(R.id.loading_icon)
    protected ImageView loadingIcon;

    @Bind(R.id.download_progress)
    protected TextView downloadProgress;

    @Bind(R.id.download_tips)
    protected TextView downloadTips;

    @Bind(R.id.progress_bar)
    protected ProgressBar progressBar;

    private boolean isGridView = true;
    private boolean isIdolizedFace = true;
    private HashMap<Integer, String> filterMap;
    private List<CardModel> cardModelList;
    private List<CardModel> visibleCardModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Glide.with(this).load(R.drawable.loading).asGif().into(loadingIcon);
        initialiseFilterMap();

        new Thread(new LoadActivityData(this)).start();
    }

    private void initialiseFilterMap() {
        filterMap = new HashMap<>();
        filterMap.put(R.id.rarity_spinner, getResources().getStringArray(R.array.rarity_array)[0]);
        filterMap.put(R.id.attribute_spinner, getResources().getStringArray(R.array.attribute_array)[0]);
        filterMap.put(R.id.grade_spinner, getResources().getStringArray(R.array.grade_array)[0]);
        filterMap.put(R.id.idol_spinner, getResources().getStringArray(R.array.idol_array)[0]);
        filterMap.put(R.id.sub_team_spinner, getResources().getStringArray(R.array.sub_team_array)[0]);
        filterMap.put(R.id.skill_type_spinner, getResources().getStringArray(R.array.skill_type_array)[0]);
        filterMap.put(R.id.event_spinner, getResources().getStringArray(R.array.is_event_card_or_not)[0]);
        filterMap.put(R.id.promo_spinner, getResources().getStringArray(R.array.is_promo_or_not)[0]);
        filterMap.put(R.id.collection_spinner, getResources().getStringArray(R.array.collection_array)[0]);
    }

    @Override
    protected void onDestroy() {
        Thread.currentThread().interrupt();
        super.onDestroy();
    }

    public void onEventMainThread(SmallCardEvent smallCardEvent) {
        findViewById(R.id.loading_mask).setVisibility(View.GONE);
        loadingIcon.setVisibility(View.GONE);
        cardModelList = smallCardEvent.getCardModelList();
        setListViewClickListener();
        setGridViewClickListener();
        visibleCardModelList = cardModelList;
        loadCardView();
    }

    public void onEventMainThread(FetchProcessEvent fetchProcessEvent) {
        if (!fetchProcessEvent.getProcess().equals("100")) {
            downloadTips.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            downloadProgress.setVisibility(View.VISIBLE);
            progressBar.setProgress(Integer.parseInt(fetchProcessEvent.getProcess()));
            downloadProgress.setText(String.format("%s%%", fetchProcessEvent.getProcess()));
        }
    }

    private void setGridViewClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardActivity.this, CardDetailActivity.class);
                CardModel cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position);
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });
    }

    private void setListViewClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardActivity.this, CardDetailActivity.class);
                CardModel cardModel = ((List<CardModel>) parent.getAdapter().getItem(0)).get(position);
                intent.putExtra("CardModel", cardModel);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_filter:
                DialogFragment filterDialogFragment = new FilterDialogFragment();
                filterDialogFragment.show(getFragmentManager(), "dialog");
                break;
            case R.id.action_grid:
                isGridView = true;
                invalidateOptionsMenu();
                break;
            case R.id.action_menu:
                isGridView = false;
                invalidateOptionsMenu();
                break;
            case R.id.action_switch_face:
                isIdolizedFace = !isIdolizedFace;
                loadCardView();
                break;
        }
        return true;
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
        removeDuplicateCard();
        sortCardListById();
        if (isGridView) {
            listView.setAdapter(new SmallCardListAdapter(CardActivity.this, emptyCardModelList, isIdolizedFace));
            gridView.setAdapter(new GridCardListAdapter(CardActivity.this, visibleCardModelList, isIdolizedFace));
        } else {
            gridView.setAdapter(new GridCardListAdapter(CardActivity.this, emptyCardModelList, isIdolizedFace));
            listView.setAdapter(new SmallCardListAdapter(CardActivity.this, visibleCardModelList, isIdolizedFace));
        }
    }

    public void onEvent(FilterEvent filterEvent) {
        if (cardModelList == null || cardModelList.size() == 0) {
            return;
        }
        filterMap.put(filterEvent.getFilterKey(), filterEvent.getFilterValue());
        visibleCardModelList = new FilterFactory().filterCards(cardModelList, filterMap);
        loadCardView();
    }

    private void sortCardListById() {
        Collections.sort(visibleCardModelList, new Comparator<CardModel>() {
            @Override
            public int compare(CardModel firstCard, CardModel secondCard) {
                return Integer.parseInt(secondCard.getCardId()) > Integer.parseInt(firstCard.getCardId()) ? 1 : -1;
            }

            @Override
            public boolean equals(Object object) {
                return false;
            }
        });
    }

    private void removeDuplicateCard() {
        HashMap<String, CardModel> cardMap = new HashMap<>();
        List<CardModel> tempVisibleCardModelList = new ArrayList<>();

        tempVisibleCardModelList.addAll(visibleCardModelList);
        visibleCardModelList.clear();

        for (CardModel c : tempVisibleCardModelList) {
            cardMap.put(c.getCardId(), c);
        }

        for (CardModel c : cardMap.values()) {
            visibleCardModelList.add(c);
        }
    }
}