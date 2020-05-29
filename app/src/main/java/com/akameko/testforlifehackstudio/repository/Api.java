package com.akameko.testforlifehackstudio.repository;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("test_task/test.php")
    Single<List<CompanyListItem>> loadCompanyListItems();

    @GET("test_task/test.php?id={id}")
    Single<List<CompanyDetailItem>> loadCompanyDetailItems(@Path("id") String id);
}
