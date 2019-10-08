package io.jonibek.feedster.domain.feeditem

import io.reactivex.Single

interface FeedItemUseCase {

    fun changeFavorite(postId: Int) : Single<Boolean>

    fun isPostFavorite(postId: Int) : Single<Boolean>

}