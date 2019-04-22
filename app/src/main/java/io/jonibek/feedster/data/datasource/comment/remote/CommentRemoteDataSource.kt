package io.jonibek.feedster.data.datasource.comment.remote

import io.jonibek.feedster.data.pojo.Comment
import io.reactivex.Single
import javax.inject.Inject

interface CommentRemoteDataSource {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

    class CommentRemoteDataSourceImpl @Inject constructor(val commentApiImpl: CommentApi.CommentApiImpl) : CommentRemoteDataSource {

        override fun getCommentsByPostId(postId: Int) = commentApiImpl.getCommentsByPostId(postId)

    }
}
