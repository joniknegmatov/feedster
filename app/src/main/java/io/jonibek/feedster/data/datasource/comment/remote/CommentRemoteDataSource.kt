package io.jonibek.feedster.data.datasource.comment.remote

import io.jonibek.feedster.data.entities.Comment
import io.reactivex.Single
import javax.inject.Inject

interface CommentRemoteDataSource {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

}
