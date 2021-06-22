package com.kmf.anaratesttask.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfoEntity(@PrimaryKey

    val id: Long? = null,

    var username: String? = null,

    var firstName: String? = null,

    var lastName: String? = null,

    var email: String? = null,

    var password: String? = null,

    var phone: String? = null,

    var userStatus: Int? = null
)
