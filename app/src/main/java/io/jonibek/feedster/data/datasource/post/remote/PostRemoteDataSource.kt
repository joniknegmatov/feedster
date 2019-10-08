package io.jonibek.feedster.data.datasource.post.remote

import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostRemoteDataSource {

    fun getAllPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

    class PostRemoteDataSourceImpl @Inject constructor(private val postsAPI: PostAPI) : PostRemoteDataSource {

        override fun getPostById(postId: Int): Single<Post> = postsAPI.getPostById(postId)

        override fun getAllPosts() = postsAPI.getAllPosts()

        override fun getPostsByUserId(userId: Int) = postsAPI.getPostsByUserId(userId)

    }
}
