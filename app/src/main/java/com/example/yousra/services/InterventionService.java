package com.example.yousra.services;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;



/**
 * Created by yousra on 22/03/17.
 */

public interface InterventionService {
    public static final String ENDPOINT = "http://148.60.11.238:27018";

    @Headers({ "Accept: application/json" })
    @GET("/adresse")
    Call<String> getAdresseIntervention();

}
