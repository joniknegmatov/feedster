package io.jonibek.feedster.util

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

fun <T> Single<T>.withNetworkSchedulers(rxSchedulers: RxSchedulers): Single<T> {
    return this.subscribeOn(rxSchedulers.network).observeOn(rxSchedulers.main)
}

fun <T> Single<T>.addTo(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(subscribe())
}