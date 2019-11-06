package com.kylestrait.codechallenge.network;

import com.kylestrait.codechallenge.data.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    String BASE_URL = "https://api.github.com/";

    @GET("users/QuickenLoans/repos")
    Call<List<Repo>> getAllRepos();
}
