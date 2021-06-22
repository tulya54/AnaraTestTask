package com.kmf.anaratesttask.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInfoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userInfoDAO(): UserInfoDAO
}