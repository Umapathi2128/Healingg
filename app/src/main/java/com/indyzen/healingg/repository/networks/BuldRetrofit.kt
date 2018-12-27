package com.indyzen.numberplatedetection.repository.networks

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Umapathi on 23/11/18.
 * Copyright Indyzen Inc, @2018
 */
open class BuldRetrofit {

    private val BASE_URL = "http://healingg.innovination.com/"

    private var buildRetrofitData: Retrofit? = null

    fun getRetrofit(): Retrofit? {

//       val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        httpClient.interceptors().add(interceptor)
//        val retrofit:Retrofit = createAdapter().build()
//        val service = retrofit.create(ServiceResponse::class.java!!)

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
//            .interceptors().add(interceptor)
            .build()

        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel
            (HttpLoggingInterceptor.Level.BODY).setLevel(HttpLoggingInterceptor.Level.HEADERS))

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        return if (buildRetrofitData == null) {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        } else buildRetrofitData
    }
}