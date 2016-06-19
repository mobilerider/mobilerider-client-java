package com.mobilerider;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MobileRiderApiClient
{
    public static final String BASE_URL = "https://api.mobilerider.com/api/";

    private MobileRiderApiClient()
    {
    }

    public static MobileRiderApiClientInterface create(String appId, String secret) throws IllegalArgumentException
    {
        if (appId == null || appId.length() == 0)
        {
            throw new IllegalArgumentException("appId");
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (secret == null)
        {
            // Add interceptor to add "appid" parameter to the query string.

            okhttp3.Interceptor interceptor =  new AddQueryStringParameterInterceptor("appid", appId);

            builder.interceptors().add(interceptor);
        }
        else
        {
            // Add interceptor to add "Authorization" header.

            String authenticationToken = (appId) + ":" + secret;

            authenticationToken = Base64.getMimeEncoder().encodeToString(authenticationToken.getBytes(StandardCharsets.UTF_8));

            okhttp3.Interceptor interceptor =  new AddHeaderInterceptor("Authorization", "Basic " + authenticationToken);

            builder.interceptors().add(interceptor);
        }

        OkHttpClient client = builder.build();

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Channel.class, new MobileRiderEntityDeserializer<Channel>());

        gsonBuilder.registerTypeAdapter(Media.class, new MobileRiderEntityDeserializer<Media>());

        Class<Page<Channel>> pageOfChannelClass = (Class) Page.class;
        gsonBuilder.registerTypeAdapter(pageOfChannelClass, new MobileRiderEntityListDeserializer<Channel>());

        Class<Page<Media>> pageOfMediaClass = (Class) Page.class;
        gsonBuilder.registerTypeAdapter(pageOfMediaClass, new MobileRiderEntityListDeserializer<Media>());

        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        return retrofit.create(MobileRiderApiClientInterface.class);
    }
}