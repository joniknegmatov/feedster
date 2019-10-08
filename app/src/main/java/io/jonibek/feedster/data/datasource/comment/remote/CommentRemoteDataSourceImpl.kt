package io.jonibek.feedster.data.datasource.comment.remote

import javax.inject.Inject

class CommentRemoteDataSourceImpl
@Inject
constructor(
    private val commentAPIImpl: CommentAPI
) : CommentRemoteDataSource {

    override fun getCommentsByPostId(postId: Int) = commentAPIImpl.getCommentsByPostId(postId)

}