package com.example.game_search.api;

import com.example.game_search.response.GameResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SetApi {

    @GET("posts")
    Call<List<GameResponse>> gamedata();
}
