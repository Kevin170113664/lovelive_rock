package com.thoughtworks.lhli.lovelive_rock.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.squareup.okhttp.ResponseBody;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.service.CardService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.medium_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        listView.setAdapter(new MediumCardListAdapter("Start Dash!"));
    }

    protected void fetchCardList() throws IOException {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://schoolido.lu/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CardService cardService = retrofit.create(CardService.class);
            Call<ResponseBody> call = cardService.getCardList();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Response response) {

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else {
            // display error
        }
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
