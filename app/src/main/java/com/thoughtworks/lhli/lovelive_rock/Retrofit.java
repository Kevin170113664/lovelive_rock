package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.service.CardService;

import retrofit.GsonConverterFactory;

public class Retrofit {


    private static Retrofit instance;

    private retrofit.Retrofit retrofit;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit();
        }
        return instance;
    }

    public Retrofit() {
        retrofit = new retrofit.Retrofit.Builder()
                .baseUrl("http://schoolido.lu/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CardService getCardService() {
        return retrofit.create(CardService.class);
    }

}
