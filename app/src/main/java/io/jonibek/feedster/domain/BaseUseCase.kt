package io.jonibek.feedster.domain

import io.reactivex.disposables.CompositeDisposable

abstract class BaseUseCase  {

    internal val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    fun clear(){
        compositeDisposable.dispose()
    }


    interface Callback<T> {

        fun onResult(result: T)

        fun onFailure(e: Throwable)

    }
}