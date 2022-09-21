package com.codingwithjks.weatherapp.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {
    private  var mCompositeDisposable: CompositeDisposable = CompositeDisposable();
    private lateinit var mNavigator: WeakReference<N>

    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }
    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

}
