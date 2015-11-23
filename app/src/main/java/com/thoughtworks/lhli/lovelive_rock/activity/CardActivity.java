package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardActivity extends AppCompatActivity {

    @Bind(R.id.small_card_list)
    protected ListView listView;

    protected List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
//        listView.setAdapter(new SmallCardListAdapter(cardList);

        ButterKnife.bind(this);
//        try {
//            cardList = new ArrayList<>();
//            fetchCardList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(CardActivity.this, CardDetailActivity.class));
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

//    protected void fetchCardList() throws IOException {
//        if (isNetworkAvailable()) {
//            Call<MultipleCards> callMultipleCards = Retrofit.getInstance().getCardService().getCardList(72);
//            callMultipleCards.enqueue(getCardListCallback());
//
//            Call<Card> callSingleCard = Retrofit.getInstance().getCardService().getCardById("315");
//            callSingleCard.enqueue(getCardByIdCallback());
//        } else {
//            System.out.print("------------------------------");
//        }
//    }

    protected Boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
