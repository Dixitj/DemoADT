package com.example.demoapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demoapp.model.Result;
import com.example.demoapp.network.NetworkBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DemoViewModel extends ViewModel {

    private MutableLiveData<Result> resultList = new MutableLiveData<>();

    public MutableLiveData<Result> dataList(){

        loadListCharacters();
        return resultList;
    }

    private void loadListCharacters() {

        Observable<Result> tempresult = NetworkBuilder.getInstance().retrofitlayer();
        tempresult.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {

                        resultList.setValue(result);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
