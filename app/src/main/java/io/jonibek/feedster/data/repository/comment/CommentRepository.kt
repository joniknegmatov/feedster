package io.jonibek.feedster.data.repository.comment

import io.jonibek.feedster.data.entities.Comment
import io.reactivex.Single

interface CommentRepository {

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

}