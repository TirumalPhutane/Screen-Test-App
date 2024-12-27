package com.example.screentestapp.util;

import com.example.screentestapp.entity.NationData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL = "https://datausa.io/";

    @GET("api/data?drilldowns=Nation&measures=Population")
    Call<JSONObject> getNations();
}
