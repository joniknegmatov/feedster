package io.jonibek.feedster.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSchedulers (
    var network : Scheduler = Schedulers.io(),
    var main: Scheduler = AndroidSchedulers.mainThread()
)