package io.jonibek.feedster.data.datasource.post.local

import io.reactivex.Single
import java.io.IOException

interface PostLocalDataSource {

    fun changeFavorite(postId: Int): Single<Boolean>

    fun isPostInFavorites(postId: Int): Single<Boolean>

    class PostLocalDataSourceImpl(private val favoritePostManager: FavoritePostManager) : PostLocalDataSource {

        override fun isPostInFavorites(postId: Int): Single<Boolean> {
            favoritePostManager.getListOfFavorites()?.let {
                return Single.just(it.contains(postId.toString()))
            }
            return Single.error(IOException("Something went wrong"))
        }

        override fun changeFavorite(postId: Int): Single<Boolean> {
            favoritePostManager.getListOfFavorites()?.let {
                val isPostInFavorites = it.contains(postId.toString())
                if (isPostInFavorites) {
                    it.remove(postId.toString())
                } else {
                    it.add(postId.toString())
                }
                favoritePostManager.saveListOfFavorites(it)
                return Single.just(!isPostInFavorites)
            }

            return Single.error(IOException("Something went wrong"))
        }

    }

}
