package com.mobilerider;

import retrofit2.http.*;
import retrofit2.Call;

interface MobileRiderApiRetrofitClientInterface
{
    @GET("channel?published=1")
    Call<Page<Channel>> channelList(@Query("page") Integer page, @Query("limit") Integer pageSize);

    @GET("channel/{id}")
    Call<Channel> channel(@Path("id") String channelId);

    @GET("media/?published=Y&mediatypeid=2&add_fields=files,views")
    Call<Page<Media>> mediaList(@Query("channelid") String channelId, @Query("page") Integer page, @Query("limit") Integer pageSize);

    @GET("media/{id}")
    Call<Media> media(@Path("id") String mediaId);
}