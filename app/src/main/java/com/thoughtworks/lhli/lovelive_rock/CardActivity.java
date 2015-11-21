package com.thoughtworks.lhli.lovelive_rock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import retrofit.Retrofit;

public class CardActivity extends AppCompatActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://schoolido.lu/api")
            .build();
    CardService cardService = retrofit.create(CardService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardService.listCards();
    }
}
