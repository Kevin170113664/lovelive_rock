package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CardService {
    @GET("cards")
    Call<MultipleCards> getCardList(@Query("page") Integer page);

    @GET("cards")
    Call<MultipleCards> getCardList();

    @GET("cards/{id}")
    Call<Card> getCardById(@Path("id") String cardId);
}
