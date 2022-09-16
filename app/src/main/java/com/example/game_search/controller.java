package com.example.game_search;

import com.example.game_search.api.SetApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller {

    private static Retrofit retrofit;
    private static final String url = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getInstance(){
        if(retrofit!=null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    SetApi getApi(){
        return retrofit.create(SetApi.class);
    }
}
