package com.kmf.anaratesttask.viewmodels

import androidx.lifecycle.MutableLiveData
import com.kmf.anaratesttask.api_clients.ApiClient
import com.kmf.anaratesttask.base_classes.BaseViewModel
import com.kmf.anaratesttask.db.UserInfoDAO
import com.kmf.anaratesttask.models.ApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserInfoViewModel(private val apiClient: ApiClient): BaseViewModel() {

    companion object {
        private const val TAG = "UserInfoViewModel"
    }

    private val response = MutableLiveData<ApiResponse>()
    fun getResponse(): MutableLiveData<ApiResponse> = response

    fun getUser(userName: String) {
        disposable.add(apiClient.client.getUser(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                response.value = ApiResponse.loading()
            }
            .subscribe( { result ->
                if (result.code() == 200) {
                    response.value = ApiResponse.success(result)
                } else {
                    response.value = ApiResponse.error(result)
                }
            }, { throwable ->
                response.value = ApiResponse.throwable(throwable)
            }))
    }
}