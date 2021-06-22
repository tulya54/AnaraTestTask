package com.kmf.anaratesttask.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.kmf.anaratesttask.utils.enumeration.Status
import retrofit2.Response

class ApiResponse private constructor(
    val status: Status, @param:Nullable @field:Nullable
    val data: Response<*>?, @param:Nullable @field:Nullable
    val error: Response<*>?, @param:Nullable @field:Nullable
    val throwabl: Throwable?) {
    companion object {

        fun loading(): ApiResponse {
            return ApiResponse(Status.LOADING, null, null, null)
        }

        fun success(@NonNull data: Response<*>): ApiResponse {
            return ApiResponse(Status.SUCCESS,  data, null, null)
        }

        fun error(@NonNull error: Response<*>): ApiResponse {
            return ApiResponse(Status.ERROR, null, error, null)
        }

        fun throwable(@NonNull throwable: Throwable): ApiResponse {
            return ApiResponse(Status.THROWABLE, null, null, throwable)
        }
    }

}