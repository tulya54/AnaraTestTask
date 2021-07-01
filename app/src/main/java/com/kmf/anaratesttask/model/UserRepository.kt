package com.kmf.anaratesttask.model

import com.kmf.anaratesttask.api_clients.ApiClient
import com.kmf.anaratesttask.db.UserInfoDAO
import com.kmf.anaratesttask.models.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val api: ApiClient,
                     private val dao: UserInfoDAO) {

    suspend fun insert(value: String) {
        withContext(Dispatchers.IO) {
            val userInfo = api.client.getUser(value) as UserInfo
            
            dao.insert(userInfoEntity = userInfo)

        }
    }
}

