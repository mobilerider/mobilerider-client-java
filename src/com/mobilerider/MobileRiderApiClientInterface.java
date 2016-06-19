package com.mobilerider;

import java.util.List;
import retrofit2.http.*;
import retrofit2.Call;

public interface MobileRiderApiClientInterface {

    @GET("channel")
    Call<Page<Channel>> channelList(@Query("page") Integer page, @Query("limit") Integer pageSize);

    @GET("channel/{id}")
    Call<Channel> channel(@Path("id") String channelId);

    @GET("media")
    Call<Page<Media>> mediaList(@Query("channelid") String channelId, @Query("limit") Integer page, @Query("limit") Integer pageSize);
}