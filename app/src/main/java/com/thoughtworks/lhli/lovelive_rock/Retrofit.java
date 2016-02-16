package com.thoughtworks.lhli.lovelive_rock;

import com.squareup.okhttp.OkHttpClient;
import com.thoughtworks.lhli.lovelive_rock.service.CardService;
import com.thoughtworks.lhli.lovelive_rock.service.EventService;

import java.util.concurrent.TimeUnit;

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
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);

        retrofit = new retrofit.Retrofit.Builder()
                .baseUrl("http://schoolido.lu/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public CardService getCardService() {
        return retrofit.create(CardService.class);
    }

    public EventService getEventService() {
        return retrofit.create(EventService.class);
    }
}
