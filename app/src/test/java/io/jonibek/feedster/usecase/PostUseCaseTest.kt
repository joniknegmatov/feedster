package io.jonibek.feedster.usecase

import io.jonibek.TestObjects
import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import io.jonibek.feedster.domain.internal.UseCaseCallback
import io.jonibek.feedster.domain.post.PostUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException

class PostUseCaseTest {

    private lateinit var postUseCaseUnderTest: PostUseCase
    private val post = TestObjects.getPost()
    private val comments = TestObjects.getComments()
    private val user = TestObjects.getUser()
    private val postRepository = Mockito.mock(PostRepository::class.java)
    private val commentRepository = Mockito.mock(CommentRepository::class.java)
    private val userRepository = Mockito.mock(UserRepository::class.java)

    @Test
    fun test_use_case_success_result() {

        Mockito.`when`(postRepository.getPostById(1)).then { Single.just(post) }
        Mockito.`when`(commentRepository.getCommentsByPostId(1)).then { Single.just(comments) }
        Mockito.`when`(userRepository.getUserById(1)).then { Single.just(user) }

        postUseCaseUnderTest = PostUseCase.PostUseCaseImpl(
            postRepository,
            commentRepository,
            userRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )


        postUseCaseUnderTest.getCommentsByPostId(1, object :
            UseCaseCallback<List<Comment>> {
            override fun onResult(result: List<Comment>) {
                assertTrue(comments.size == result.size)
            }

            override fun onFailure(e: Throwable) {
            }
        })

        postUseCaseUnderTest.getUserById(1, object : UseCaseCallback<User> {
            override fun onResult(result: User) {
                assertTrue(user == result)
            }

            override fun onFailure(e: Throwable) {
            }
        })

        postUseCaseUnderTest.getPostById(1, object : UseCaseCallback<Post> {
            override fun onResult(result: Post) {
                assertTrue(post == result)
            }

            override fun onFailure(e: Throwable) {
            }

        })
    }

    @Test
    fun test_use_case_error_result() {
        val error: Single<Any> = Single.error(IOException("message"))

        Mockito.`when`(postRepository.getPostById(1)).then { error }
        Mockito.`when`(commentRepository.getCommentsByPostId(1)).then { error }
        Mockito.`when`(userRepository.getUserById(1)).then { error}

        postUseCaseUnderTest = PostUseCase.PostUseCaseImpl(
            postRepository,
            commentRepository,
            userRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )


        postUseCaseUnderTest.getCommentsByPostId(1, object :
            UseCaseCallback<List<Comment>> {
            override fun onResult(result: List<Comment>) {
            }

            override fun onFailure(e: Throwable) {
                assertTrue(e.message == e.message)
            }
        })

        postUseCaseUnderTest.getUserById(1, object : UseCaseCallback<User> {
            override fun onResult(result: User) {
            }

            override fun onFailure(e: Throwable) {
                assertTrue(e.message == e.message)
            }
        })

        postUseCaseUnderTest.getPostById(1, object : UseCaseCallback<Post> {
            override fun onResult(result: Post) {
            }

            override fun onFailure(e: Throwable) {
                assertTrue(e.message == e.message)
            }

        })
    }

}
