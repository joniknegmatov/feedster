package io.jonibek.feedster.data.datasource.post

import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.pojo.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostRepository {

    fun getAllPosts(): Single<List<Post>>

    fun getPostsByUserId(userId: Int): Single<List<Post>>

    fun getPostById(postId : Int): Single<Post>

    class PostRepositoryImpl @Inject constructor(val postRemoteDataSource: PostRemoteDataSource) : PostRepository {

        override fun getPostById(postId: Int) = postRemoteDataSource.getPostById(postId)

        override fun getAllPosts() = postRemoteDataSource.getAllPosts()

        override fun getPostsByUserId(userId: Int) = postRemoteDataSource.getPostsByUserId(userId)

    }
}
