package io.jonibek.feedster.domain.post

import io.jonibek.feedster.data.repository.comment.CommentRepository
import io.jonibek.feedster.data.repository.post.PostRepository
import io.jonibek.feedster.data.repository.user.UserRepository
import javax.inject.Inject

class PostUseCaseImpl
@Inject constructor(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
) : PostUseCase {

    override fun getUserById(userId: Int) = userRepository.getUserById(userId)

    override fun getPostById(postId: Int) = postRepository.getPostById(postId)

    override fun getCommentsByPostId(postId: Int) = commentRepository.getCommentsByPostId(postId)

}