package com.mobilerider;

import org.junit.Test;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class testclassTest {
    @org.junit.Before
    public void setUp() throws Exception {


    }

    @Test
    public void successfulTest() {

        //Retrofit retrofit = new Retrofit.Builder().baseUrl(new HttpUrl()).build();
//                .baseUrl(new String("https://api.mobilerider.com/api/"))
  //              .build();

    //    MobileRiderInterface service = retrofit.create(MobileRiderInterface.class);
    }

    @Test(expected=Exception.class)
    public void failingTest() {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}