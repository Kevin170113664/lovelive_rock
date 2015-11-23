package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    protected List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        try {
            cardList = new ArrayList<>();
            fetchCardList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void fetchCardList() throws IOException {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Call<MultipleCards> callMultipleCards = Retrofit.getInstance().getCardService().getCardList(72);
            callMultipleCards.enqueue(getCardListCallback());

            Call<Card> callSingleCard = Retrofit.getInstance().getCardService().getCardById("315");
            callSingleCard.enqueue(getCardByIdCallback());
        } else {
            System.out.print("------------------------------");
        }
    }

    @NonNull
    private Callback<MultipleCards> getCardListCallback() {
        return new Callback<MultipleCards>() {
            @Override
            public void onResponse(Response<MultipleCards> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardList.addAll(Arrays.asList(response.body().getResults()));
                    listView.setAdapter(new MediumCardListAdapter(MainActivity.this, cardList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardListCallback failed.");
            }
        };
    }

    @NonNull
    private Callback<Card> getCardByIdCallback() {
        return new Callback<Card>() {
            @Override
            public void onResponse(Response<Card> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardList.add(response.body());
                    listView.setAdapter(new MediumCardListAdapter(MainActivity.this, cardList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardByIdCallback failed.");
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_song || id == R.id.action_event) {
            return true;
        }
        if (item.getItemId() == R.id.action_card) {
            startActivity(new Intent(this, CardActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
