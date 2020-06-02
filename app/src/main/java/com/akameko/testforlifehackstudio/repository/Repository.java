package com.akameko.testforlifehackstudio.repository;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Repository {

    private Retrofit retrofit;
    private Api api;

    public Repository() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://megakohz.bget.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }


    public Single<List<CompanyListItem>> loadCompanies() {
        return api.loadCompanyListItems();
    }

    public Single<List<CompanyDetailItem>> loadCompaniesDetails(String id) {
        return api.loadCompanyDetailItems(id);
    }





}
