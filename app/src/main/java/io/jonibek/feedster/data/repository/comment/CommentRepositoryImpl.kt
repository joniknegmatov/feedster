package io.jonibek.feedster.data.repository.comment

import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import javax.inject.Inject

class CommentRepositoryImpl
@Inject
constructor(private val commentsRemoteDataSource: CommentRemoteDataSource) :
    CommentRepository {

    override fun getCommentsByPostId(postId: Int) = commentsRemoteDataSource.getCommentsByPostId(postId)

}