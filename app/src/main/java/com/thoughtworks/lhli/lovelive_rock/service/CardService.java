package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.Card;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface CardService {
    @GET("/cards")
    Call<List<Card>> getCardList();
}
