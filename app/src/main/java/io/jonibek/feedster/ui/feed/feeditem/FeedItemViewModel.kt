package io.jonibek.feedster.ui.feed.feeditem

import android.databinding.Bindable
import io.jonibek.feedster.BR
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.internal.UseCaseCallback
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.ui.ObservableViewModel

class FeedItemViewModel(val post: Post, private val feedItemUseCase: FeedItemUseCase) : ObservableViewModel() {


    @get:Bindable
    var postTitle : String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.postTitle)
    }

    @get:Bindable
    var postBody : String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.postBody)
    }

    @get:Bindable
    var isPostFavorite: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.postFavorite)
        }

    private val favoritePostCallback = object : UseCaseCallback<Boolean> {
        override fun onResult(result: Boolean) {
            isPostFavorite = result
        }
    }

    init {
        postTitle = post.title
        postBody = post.body
        feedItemUseCase.isPostFavorite(post.id!!, favoritePostCallback)
    }

    fun changeFavorite() {
        feedItemUseCase.changeFavorite(post.id!!, favoritePostCallback)
    }




}