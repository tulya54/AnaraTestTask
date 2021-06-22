package com.kmf.anaratesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import java.io.Serializable

data class UserInfo(@Body

    @SerializedName("id")
    @Expose
    var id: Long = 0,

    @SerializedName("username")
    @Expose
    var username: String? = null,

    @SerializedName("firstName")
    @Expose
    var firstName: String? = null,

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("password")
    @Expose
    var password: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("userStatus")
    @Expose
    var userStatus: Int? = null
): Serializable
