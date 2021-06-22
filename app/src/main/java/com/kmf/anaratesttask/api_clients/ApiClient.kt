package com.kmf.anaratesttask.api_clients

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kmf.anaratesttask.Constant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClient(val context: Context?) {

    private val rxAdapter = RxJava2CallAdapterFactory.create()
    private val gson = GsonBuilder().setLenient().create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor() { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            val request = requestBuilder.build()
            return@addInterceptor chain.proceed(request)
        }.build()

    val client: IApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .client(okHttpClient)
            .build()
        retrofit.create(IApiService::class.java)
    }
}