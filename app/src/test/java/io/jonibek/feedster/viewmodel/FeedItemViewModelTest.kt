package io.jonibek.feedster.viewmodel

import io.jonibek.feedster.TestObjects
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.internal.UseCaseCallback
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.presentation.feed.feeditem.FeedItemViewModel
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FeedItemViewModelTest : BaseViewModelTest() {

    private lateinit var viewModelUnderTest: FeedItemViewModel
    private var post: Post = TestObjects.getPost()

    @Test
    fun test_state() {
        viewModelUnderTest = FeedItemViewModel(post, object : FeedItemUseCase {

            override fun changeFavorite(postId: Int, callback: UseCaseCallback<Boolean>) {
                callback.onResult(true)
            }

            override fun isPostFavorite(postId: Int, callback: UseCaseCallback<Boolean>) {
                callback.onResult(false)
            }

        })

        assertTrue(viewModelUnderTest.postTitle == post.title)
        assertTrue(viewModelUnderTest.postBody == post.body)
        assertFalse(viewModelUnderTest.isPostFavorite)
        viewModelUnderTest.changeFavorite()
        assertTrue(viewModelUnderTest.isPostFavorite)
    }


}
