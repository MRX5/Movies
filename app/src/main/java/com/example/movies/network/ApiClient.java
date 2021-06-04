package com.example.movies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit mInstance = null;
    public static final String BASE_URL="http://api.themoviedb.org/3/";

    public static synchronized Retrofit getInstance() {
        if(mInstance==null) {
            mInstance= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mInstance;
    }
}
