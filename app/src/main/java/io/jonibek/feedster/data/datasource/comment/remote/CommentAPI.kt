package io.jonibek.feedster.data.datasource.comment.remote

import io.jonibek.feedster.data.entities.Comment
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentAPI {

    @GET("comments")
    fun getCommentsByPostId(@Query ("postId") postId : Int) : Single<List<Comment>>

}