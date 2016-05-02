package com.thoughtworks.lhli.lovelive_rock.service;

import com.thoughtworks.lhli.lovelive_rock.model.MultipleSongs;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SongService {

    @GET("songs")
    Call<MultipleSongs> getSongList(@Query("page") Integer page,
                                    @Query("expand_event") Boolean expandEvent);

    @GET("songs")
    Call<MultipleSongs> getSongByPageSize(@Query("page") Integer page,
                                          @Query("page_size") Integer pageSize);

}
