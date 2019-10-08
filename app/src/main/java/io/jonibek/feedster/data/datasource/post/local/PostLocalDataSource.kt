package io.jonibek.feedster.data.datasource.post.local

import io.reactivex.Single

interface PostLocalDataSource {

    fun changeFavorite(postId: Int): Single<Boolean>

    fun isPostInFavorites(postId: Int): Single<Boolean>

}
