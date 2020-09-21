package com.example.demoapp.network;

import com.example.demoapp.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IAPIService {

    @GET("/api/character/")
    Observable<Result> getCharacterList();
}
