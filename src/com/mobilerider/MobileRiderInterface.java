package com.mobilerider;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface MobileRiderInterface {

    @GET("channel")
    Call<List<Channel>> channelList(@Query("limit") Integer page, @Query("limit") Integer pageSize);

    @GET("media")
    Call<List<Media>> mediaList(@Query("channelid") Integer channelId);
}