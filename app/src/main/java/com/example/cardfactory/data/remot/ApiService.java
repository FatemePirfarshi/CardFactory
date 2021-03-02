package com.example.cardfactory.data.remot;

import com.example.cardfactory.data.model.Card;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

    String BASE_URL = "http://static.pushe.co/challenge/json/";

    @GET
    Call<List<Card>> getCardItems(@Url String url);

}
