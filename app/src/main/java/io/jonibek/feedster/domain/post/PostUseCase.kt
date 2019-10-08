package io.jonibek.feedster.domain.post

import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import io.reactivex.Single

interface PostUseCase {

    fun getUserById(userId: Int): Single<User>

    fun getPostById(postId: Int): Single<Post>

    fun getCommentsByPostId(postId: Int): Single<List<Comment>>

}