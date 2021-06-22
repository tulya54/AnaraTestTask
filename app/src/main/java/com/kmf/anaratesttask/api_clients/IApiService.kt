package com.kmf.anaratesttask.api_clients

import com.google.gson.JsonObject
import com.kmf.anaratesttask.models.UserInfo
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface IApiService {

    @Headers("Content-Type: application/json")
    @POST("v2/user")
    fun signUp(@Body body: UserInfo): Observable<Response<JsonObject>>

    @GET("v2/user/{username}")
    fun getUser(@Path("username") username: String):
            Observable<Response<UserInfo>>
}