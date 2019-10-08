package io.jonibek.feedster.presentation.feed

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.presentation.internal.ObservableViewModel
import io.jonibek.feedster.util.RxSchedulers
import io.jonibek.feedster.util.addTo
import io.jonibek.feedster.util.withNetworkSchedulers
import javax.inject.Inject

class FeedsFragmentViewModel
@Inject
constructor(
    private val feedsUseCase: FeedsUseCase,
    private val rxSchedulers: RxSchedulers
) : ObservableViewModel() {

    val postsLiveData: MutableLiveData<List<Post>?> by lazy { MutableLiveData<List<Post>?>() }

    @get:Bindable
    var loadingInProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingInProgress)
        }

    @get:Bindable
    var showReloadButton: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showReloadButton)
        }

    fun loadPosts() {
        feedsUseCase
            .getAllPosts()
            .withNetworkSchedulers(rxSchedulers)
            .doOnSubscribe {
                showReloadButton = false
                loadingInProgress = true
            }
            .doOnSuccess { result ->
                loadingInProgress = false
                postsLiveData.value = result
            }
            .doOnError {
                loadingInProgress = false
                showReloadButton = postsLiveData.value == null
            }
            .addTo(compositeDisposable)
    }

}