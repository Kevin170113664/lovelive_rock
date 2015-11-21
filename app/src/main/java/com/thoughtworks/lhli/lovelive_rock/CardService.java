package com.thoughtworks.lhli.lovelive_rock;

import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CardService {
    @GET("/cards")
    Call<List<Card>> listCards();
}
