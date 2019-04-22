package io.jonibek.feedster.data.datasource.post.remote

import io.jonibek.feedster.data.pojo.Post
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

interface PostApi {

    @GET("posts")
    fun getAllPosts() : Single<List<Post>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId : Int) : Single<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") postId: Int): Single<Post>

    class PostApiImpl @Inject constructor(retrofit: Retrofit) : PostApi {
        private val postsApi by lazy { retrofit.create(PostApi::class.java) }

        override fun getAllPosts() = postsApi.getAllPosts()

        override fun getPostsByUserId(userId: Int) = postsApi.getPostsByUserId(userId)

        override fun getPostById(postId: Int) = postsApi.getPostById(postId)

    }

}