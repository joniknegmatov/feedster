package io.jonibek.feedster.data.datasource.comment

import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.entities.Comment
import io.reactivex.Single
import javax.inject.Inject

interface CommentRepository {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

    class CommentRepositoryImpl @Inject constructor(private val commentsRemoteDataSource: CommentRemoteDataSource) : CommentRepository {

        override fun getCommentsByPostId(postId: Int)= commentsRemoteDataSource.getCommentsByPostId(postId)

    }
}