package io.jonibek.feedster.data.datasource.post

import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostRepository {

    fun getAllPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

    fun changeFavorite(postId: Int): Single<Boolean>

    fun isPostInFavorites(postId: Int): Single<Boolean>


    class PostRepositoryImpl @Inject constructor(
        private val postRemoteDataSource: PostRemoteDataSource,
        private val postLocalDataSource: PostLocalDataSource
    ) : PostRepository {

        override fun isPostInFavorites(postId: Int) = postLocalDataSource.isPostInFavorites(postId)

        override fun changeFavorite(postId: Int) = postLocalDataSource.changeFavorite(postId)

        override fun getPostById(postId: Int) = postRemoteDataSource.getPostById(postId)

        override fun getAllPosts() = postRemoteDataSource.getAllPosts()

        override fun getPostsByUserId(userId: Int) = postRemoteDataSource.getPostsByUserId(userId)

    }
}
