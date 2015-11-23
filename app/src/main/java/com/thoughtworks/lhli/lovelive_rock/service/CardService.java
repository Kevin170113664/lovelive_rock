package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CardService {
    @GET("cards")
    public void getCardList(Callback<List<Card>> callback);

    @GET("cards/{id}")
    Call<Card> getCardById(@Path("id") String cardId);
}
