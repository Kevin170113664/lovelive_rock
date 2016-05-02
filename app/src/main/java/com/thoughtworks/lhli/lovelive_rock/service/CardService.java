package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CardService {

    @GET("cards")
    Call<MultipleCards> getCardList(@Query("page") Integer page,
                                    @Query("expand_event") Boolean expandEvent);

    @GET("cards/{id}")
    Call<CardModel> getCardById(@Path("id") String cardId);

    @GET("cardids")
    Call<Integer[]> getCardId();
}
