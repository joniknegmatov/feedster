package io.jonibek.feedster.presentation.feed.feeditem

import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.presentation.internal.ObservableViewModel
import io.jonibek.feedster.util.addTo


class FeedItemViewModel
constructor(
    private val post: Post,
    private val feedItemUseCase: FeedItemUseCase
) : ObservableViewModel() {


    @get:Bindable
    var postTitle: String = post.title
        set(value) {
            field = value
            notifyPropertyChanged(BR.postTitle)
        }

    @get:Bindable
    var postBody: String = post.body
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

    fun checkIfPostIsFavorite() {
        post.id?.let { postId ->
            feedItemUseCase
                .isPostFavorite(postId)
                .doOnSuccess {
                    isPostFavorite = it
                }
                .addTo(compositeDisposable)
        }
    }

    fun changeFavorite() {
        post.id?.let { postId ->
            feedItemUseCase
                .changeFavorite(postId)
                .doOnSuccess {
                    isPostFavorite = it
                }
                .addTo(compositeDisposable)
        }
    }


}