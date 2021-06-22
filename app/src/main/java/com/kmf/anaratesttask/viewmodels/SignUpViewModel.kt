package com.kmf.anaratesttask.viewmodels

import androidx.lifecycle.MutableLiveData
import com.kmf.anaratesttask.api_clients.ApiClient
import com.kmf.anaratesttask.base_classes.BaseViewModel
import com.kmf.anaratesttask.models.ApiResponse
import com.kmf.anaratesttask.models.UserInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class SignUpViewModel(private val apiClient: ApiClient): BaseViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    private val response = MutableLiveData<ApiResponse>()
    fun getResponse(): MutableLiveData<ApiResponse> = response

    fun signUp(value: UserInfo) {
        disposable.add(apiClient.client.signUp(value)
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