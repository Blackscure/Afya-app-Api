package com.buyahi.afya_app.network;


import com.buyahi.afya_app.YelpBusinessesSerchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBusinessesSerchResponse> getRestaurants(
            @Query("location") String location,
            @Query("term") String term
    );
}
