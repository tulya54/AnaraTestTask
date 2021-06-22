package com.kmf.anaratesttask.di

import androidx.room.Room
import com.kmf.anaratesttask.Constant.USER_INFO_DATABASE_NAME
import com.kmf.anaratesttask.api_clients.ApiClient
import com.kmf.anaratesttask.db.AppDatabase
import com.kmf.anaratesttask.viewmodels.SignUpViewModel
import com.kmf.anaratesttask.viewmodels.UserInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SignUpViewModel(apiClient = get()) }
    viewModel { UserInfoViewModel(apiClient = get()) }
    single { ApiClient(androidContext()) }
    single { Room.databaseBuilder(androidApplication(),
        AppDatabase::class.java, USER_INFO_DATABASE_NAME)
        .build()
    }
    single { get<AppDatabase>().userInfoDAO() }
    single<CoroutineScope> { GlobalScope }
}



/*
fun providesDatabase(application: Application):AppDatabase =
    Room.databaseBuilder(application,
        AppDatabase::class.java,
        USER_INFO_DATABASE_NAME)
        .build()
fun providesDao(db:AppDatabase): UserInfoDAO = db.userInfoDAO()
 */

