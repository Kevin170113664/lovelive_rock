package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.service.CardService;

import retrofit.Retrofit;

public class RestClient {


    private static RestClient instance;

    private Retrofit retrofit;

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public RestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolido.lu/api/")
                .build();
    }

    public CardService getCardService() {
        return retrofit.create(CardService.class);
    }

}
