package io.jonibek.feedster.ui.feed

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.Bindable
import io.jonibek.feedster.BR
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.domain.internal.UseCaseCallback
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

    private val postsCallback = object : UseCaseCallback<List<Post>> {
        override fun onResult(result: List<Post>) {
            loadingInProgress = false
            postsLiveData.value = result
        }

        override fun onFailure(e: Throwable) {
            loadingInProgress = false
            showReloadButton = postsLiveData.value == null
        }
    }

    override fun onCleared() {
        super.onCleared()
        feedsUseCase.clear()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val feedsUseCase: FeedsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedsFragmentViewModel::class.java)) {
                return FeedsFragmentViewModel(feedsUseCase) as T
            }
            throw IllegalArgumentException("You can create only FeedsFragmentViewModel class instance with this factory")
        }
    }
}