package io.jonibek.feedster.data.datasource.post.remote

import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostRemoteDataSource {

    fun getAllPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

    class PostRemoteDataSourceImpl @Inject constructor(val postsApi: PostApi) : PostRemoteDataSource {

        override fun getPostById(postId: Int): Single<Post> = postsApi.getPostById(postId)

        override fun getAllPosts() = postsApi.getAllPosts()

        override fun getPostsByUserId(userId: Int) = postsApi.getPostsByUserId(userId)

    }
}
