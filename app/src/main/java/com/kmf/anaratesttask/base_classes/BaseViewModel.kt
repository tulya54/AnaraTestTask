package com.kmf.anaratesttask.base_classes

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
       // const val BEARER = "Bearer "
    }

    protected val disposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        if (disposable.size() > 0) {
            disposable.clear()
        }
    }
}