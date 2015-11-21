package com.thoughtworks.lhli.lovelive_rock.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.service.CardService;

import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;

public class CardActivity extends AppCompatActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://schoolido.lu/api")
            .build();
    CardService cardService = retrofit.create(CardService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Call<List<Card>> cardList = cardService.getCardList();
    }
}
