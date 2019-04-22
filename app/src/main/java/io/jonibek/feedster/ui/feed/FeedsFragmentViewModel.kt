package io.jonibek.feedster.ui.feed

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.Bindable
import io.jonibek.feedster.BR
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.domain.BaseUseCase
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.ui.ObservableViewModel
import javax.inject.Inject

class FeedsFragmentViewModel @Inject constructor(private val feedsUseCase: FeedsUseCase) : ObservableViewModel() {

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
        showReloadButton = false
        loadingInProgress = true
        feedsUseCase.getAllPosts(postsCallback)
    }

    private val postsCallback = object : BaseUseCase.Callback<List<Post>?> {
        override fun onResult(result: List<Post>?) {
            loadingInProgress = false
            postsLiveData.value = result
        }

        override fun onFailure(e: Throwable) {
            loadingInProgress = false
            showReloadButton = postsLiveData.value == null
        }

    }

    override fun onCleared() {
        feedsUseCase.clear()
    }

    class Factory @Inject constructor(private val feedsUseCase: FeedsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedsFragmentViewModel(feedsUseCase) as T
        }
    }

}