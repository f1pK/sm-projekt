package com.example.zadanie_3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityService {
    @GET("/v1/city")
    Call<List<City>> fetchCity(
            @Query("X-Api-Key") String apiKey,
            @Query("name") String cityName
    );
}
