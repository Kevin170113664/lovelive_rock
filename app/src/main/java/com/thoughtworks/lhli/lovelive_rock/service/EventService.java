package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.MultipleEvents;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface EventService {

    @GET("events")
    Call<MultipleEvents> getLatestEvent(@Query("ordering") String ordering,
                                        @Query("page_size") Integer pageSize);
}
