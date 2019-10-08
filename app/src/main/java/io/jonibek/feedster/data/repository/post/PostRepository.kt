package io.jonibek.feedster.data.repository.post

import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single

interface PostRepository {

    fun getAllPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

    fun changeFavorite(postId: Int): Single<Boolean>

    fun isPostInFavorites(postId: Int): Single<Boolean>

}
