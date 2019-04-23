package io.jonibek.feedster.usecase

import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.UseCaseCallback
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException

class FeedsItemUseCaseTest {

    private lateinit var feedItemUseCase: FeedItemUseCase
    private var postRepository: PostRepository = Mockito.mock(PostRepository::class.java)
    private var singleFalse = Single.just(false)
    private var singleTrue = Single.just(true)


    @Before
    fun init() {
        feedItemUseCase = FeedItemUseCase.FeedItemUseCaseImpl(
            postRepository, Schedulers.trampoline(), Schedulers.trampoline()
        )

    }

    @Test
    fun test_use_case_success_result() {
        Mockito.`when`(postRepository.isPostInFavorites(1)).then { singleTrue }
        Mockito.`when`(postRepository.changeFavorite(1)).then { singleFalse }

        feedItemUseCase.isPostFavorite(1, object : UseCaseCallback<Boolean> {
            override fun onResult(result: Boolean) {
                assertTrue(result)
            }

        })

        feedItemUseCase.changeFavorite(1, object : UseCaseCallback<Boolean> {
            override fun onResult(result: Boolean) {
                assertFalse(result)
            }

        })


    }

    @Test
    fun test_use_case_error() {
        val error: Single<List<Post>> = Single.error(IOException("message"))
        Mockito.`when`(postRepository.isPostInFavorites(1)).then { error }
        Mockito.`when`(postRepository.changeFavorite(1)).then { error }

        val callback = object : UseCaseCallback<Boolean> {
            override fun onResult(result: Boolean) {

            }

            override fun onFailure(e: Throwable) {
                assert(e.message == "message")
            }

        }

        feedItemUseCase.isPostFavorite(1, callback)
        feedItemUseCase.changeFavorite(1, callback)
    }
}