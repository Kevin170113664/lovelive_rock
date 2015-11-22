package com.thoughtworks.lhli.lovelive_rock.service;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.thoughtworks.lhli.lovelive_rock.model.Card;


import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;

public interface CardService {
    @GET("cards")
    Call<ResponseBody> getCardList();
}
