package com.indyzen.numberplatedetection.repository.networks

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Part
import rx.Observable


/**
 * Created by Umapathi on 23/11/18.
 * Copyright Indyzen Inc, @2018
 */
interface RetrofitConfig {
    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<ServiceResponse>

}