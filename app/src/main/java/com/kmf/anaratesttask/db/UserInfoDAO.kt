package com.kmf.anaratesttask.db

import androidx.room.*
import rx.Single

@Dao
interface UserInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfoEntity: UserInfoEntity)

    @Update
    fun update(userInfoEntity: UserInfoEntity)

    @Delete
    fun delete(userInfoEntity: UserInfoEntity)

    @Query("SELECT * FROM user_info")
    fun getUserInfo(): UserInfoEntity?
}