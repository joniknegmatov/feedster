package io.jonibek.feedster.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BaseUseCase(private val subscribeScheduler: Scheduler, private val observeScheduler: Scheduler) : BaseUseCaseInterface {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    fun <T> addDisposable(observable: Single<T>, callback: UseCaseCallback<T>) {
        compositeDisposable.add(
            observable
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe({ callback.onResult(it) }, { callback.onFailure(it) })
        )
    }

    override fun clear() {
        compositeDisposable.dispose()
    }
}

interface UseCaseCallback<T> {

    fun onResult(result: T)

    fun onFailure(e: Throwable)

}

interface BaseUseCaseInterface{

    fun clear(){

    }
}