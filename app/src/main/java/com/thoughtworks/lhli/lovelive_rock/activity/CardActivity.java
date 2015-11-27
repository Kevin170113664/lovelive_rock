package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.GridCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.adapter.SmallCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SmallCardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.task.LoadActivityData;

import java.util.ArrayList;
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

    @Bind(R.id.rarity_spinner)
    protected Spinner raritySpinner;

    @Bind(R.id.idol_spinner)
    protected Spinner idolSpinner;

    @Bind(R.id.attribute_spinner)
    protected Spinner attributeSpinner;

    @Bind(R.id.grade_spinner)
    protected Spinner gradeSpinner;

    @Bind(R.id.sub_team_spinner)
    protected Spinner subTeamSpinner;

    @Bind(R.id.skill_type_spinner)
    protected Spinner skillTypeSpinner;

    //    @OnClick({R.id.filter_n, R.id.filter_r, R.id.filter_sr, R.id.filter_ur})
//    public void filterCardByRarity(View view) {
//        switch (((Button) view).getText().toString()) {
//            case "N":
//                filterCardByRarity("N");
//                break;
//            case "R":
//                filterCardByRarity("R");
//                break;
//            case "SR":
//                filterCardByRarity("SR");
//                break;
//            case "UR":
//                filterCardByRarity("UR");
//                break;
//            default:
//                break;
//        }
//    }
    private ArrayAdapter<CharSequence> adapter;
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
        setDropDownList();
    }

    private void setDropDownList() {
        adapter = ArrayAdapter.createFromResource(this,
                R.array.rarity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raritySpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.idol_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idolSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.attribute_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attributeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.sub_team_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subTeamSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.skill_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillTypeSpinner.setAdapter(adapter);
    }

    public void onEventMainThread(SmallCardEvent smallCardEvent) {
        findViewById(R.id.loading_mask).setVisibility(View.GONE);
        loadingIcon.setVisibility(View.GONE);
        cardModelList = smallCardEvent.getCardModelList();
        setListViewClickListener();
        setGridViewClickListener();
    }

    public void onEventMainThread(FetchProcessEvent fetchProcessEvent) {
        Toast.makeText(getApplicationContext(), fetchProcessEvent.getProcess(),
                Toast.LENGTH_SHORT).show();
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