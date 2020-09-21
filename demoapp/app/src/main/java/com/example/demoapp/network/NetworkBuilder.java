package com.example.demoapp.network;

import com.example.demoapp.model.Result;
import com.google.gson.Gson;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {

    private static NetworkBuilder networkBuilder;

    private NetworkBuilder(){}

    //Instance of the Netwrok Layer
    public static  NetworkBuilder getInstance(){

        if(networkBuilder == null)
            return new NetworkBuilder();
        else
          return   networkBuilder;
    }

    public Observable<Result> retrofitlayer() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl("https://rickandmortyapi.com")
                .build();

        IAPIService apiService = retrofit.create(IAPIService.class);
        return apiService.getCharacterList();
    }
}
