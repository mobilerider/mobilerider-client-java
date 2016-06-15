package com.mobilerider;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MobileRiderApiClient
{
    public static final String BASE_URL = "https://api.mobilerider.com/api/";

    private MobileRiderApiClient()
    {
    }

    public static MobileRiderApiClientInterface create(String appId, String secret) throws IllegalArgumentException {
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

        builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(MobileRiderApiClientInterface.class);
    }
}