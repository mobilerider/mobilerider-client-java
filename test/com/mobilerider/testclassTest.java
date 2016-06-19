package com.mobilerider;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import org.junit.Test;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class testclassTest {
    @org.junit.Before
    public void setUp() throws Exception {


    }

    @Test
    public void successfulTest() throws IOException {
        MobileRiderApiClientInterface client = MobileRiderApiClient.create("0e1c3c87eafc51fb3c3aedd958010bf2", null);

        Channel channel = client.channel("19077").execute().body();

        Page<Channel> channels = client.channelList(1, 5).execute().body();

        Page<Media> videos = client.mediaList(channel.getId(), 1, 5).execute().body();

        Media video = client.media("103701").execute().body();
    }

    @Test(expected=Exception.class)
    public void failingTest() {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}