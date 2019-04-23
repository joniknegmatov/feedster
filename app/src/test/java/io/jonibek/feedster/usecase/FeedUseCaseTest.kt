package io.jonibek.feedster.usecase

import io.jonibek.TestObjects
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.UseCaseCallback
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException

class FeedUseCaseTest {

    private lateinit var feedUseCaseUnderTest: FeedsUseCase
    private val posts = TestObjects.getPostList()
    private var postRepository: PostRepository = Mockito.mock(PostRepository::class.java)

    @Before
    fun init() {
    }

    @Test
    fun test_use_case_success_result() {
        Mockito.`when`(postRepository.getAllPosts()).then { Single.just(posts) }

        feedUseCaseUnderTest = FeedsUseCase.FeedsUseCaseImpl(
            postRepository, Schedulers.trampoline(), Schedulers.trampoline()
        )

        val callback = object : UseCaseCallback<List<Post>> {
            override fun onResult(result: List<Post>) {
                assertTrue(result.size == posts.size)
            }

            override fun onFailure(e: Throwable) {
            }

        }

        feedUseCaseUnderTest.getAllPosts(callback)
    }

    @Test
    fun test_use_case_error() {
        val error: Single<List<Post>> = Single.error(IOException("message"))
        Mockito.`when`(postRepository.getAllPosts()).then { error }

        feedUseCaseUnderTest = FeedsUseCase.FeedsUseCaseImpl(
            postRepository, Schedulers.trampoline(), Schedulers.trampoline()
        )

        val callback = object : UseCaseCallback<List<Post>> {
            override fun onResult(result: List<Post>) {

            }

            override fun onFailure(e: Throwable) {
                assert(e.message == "message")
            }

        }

        feedUseCaseUnderTest.getAllPosts(callback)
    }
}