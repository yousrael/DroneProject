package com.example.yousra.services;
import com.example.yousra.objects.Drone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by yousra on 15/02/17.
 */

public interface DroneService {
    public static final String ENDPOINT = "http://148.60.11.238:27018";

    @Headers({ "Accept: application/json" })
    @GET("/position")
   Call<Drone> getPosition();

    @POST("/position")
    Call<Drone> setPosition(@Body Drone position);
}
