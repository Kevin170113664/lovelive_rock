package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.squareup.okhttp.ResponseBody;
import com.thoughtworks.lhli.lovelive_rock.R;
import com.thoughtworks.lhli.lovelive_rock.adapter.MediumCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.adapter.SmallCardListAdapter;
import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.service.CardService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

public class CardActivity extends AppCompatActivity {

    @Bind(R.id.small_card_list)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        ButterKnife.bind(this);
        listView.setAdapter(new SmallCardListAdapter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
