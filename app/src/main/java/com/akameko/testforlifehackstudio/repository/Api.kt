package com.akameko.testforlifehackstudio.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("test_task/test.php")
    fun loadCompanyListItems(): Single<List<CompanyListItem?>?>?

    //@GET("test_task/test.php?id={id}")
    @GET("test_task/test.php")
    fun loadCompanyDetailItems(@Query("id") id: String?): Single<List<CompanyDetailItem?>?>?
}