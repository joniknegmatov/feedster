package io.jonibek.feedster.data.datasource.comment.remote

import io.jonibek.feedster.data.entities.Comment
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface CommentApi {

    @GET("comments")
    fun getCommentsByPostId(@Query ("postId") postId : Int) : Single<List<Comment>>

    class CommentApiImpl @Inject constructor(retrofit: Retrofit) : CommentApi {

        private val commentsApi by lazy { retrofit.create(CommentApi::class.java) }

        override fun getCommentsByPostId(postId: Int) = commentsApi.getCommentsByPostId(postId)

    }
}