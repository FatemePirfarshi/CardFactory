package com.example.cardfactory.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.cardfactory.data.model.Card;
import com.example.cardfactory.data.remot.ApiService;
import com.example.cardfactory.data.remot.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardRepository {

    public static final String TAG = "CardRepository";

    private static CardRepository sInstance;
    private ApiService mApiService;

    private MutableLiveData<List<Card>> mCardsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Card>> getCardsLiveData() {
        return mCardsLiveData;
    }

    public static CardRepository getInstance() {
        if (sInstance == null)
            sInstance = new CardRepository();
        return sInstance;
    }

    private CardRepository() {
        mApiService = RetrofitInstance.getCardInstance().create(ApiService.class);
    }

    public void fetchCardItems() {

        Call<List<Card>> call = mApiService.getCardItems("http://static.pushe.co/challenge/json");
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                mCardsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
