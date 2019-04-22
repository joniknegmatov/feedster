package io.jonibek.feedster.viewmodel

import io.jonibek.TestObjects
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.ui.feed.FeedsFragmentViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FeedsViewModelTest : BaseViewModelTest(){

    private lateinit var viewModelUnderTest: FeedsFragmentViewModel

    @Test
    fun test_preload_view_state() {
        viewModelUnderTest = FeedsFragmentViewModel(object : FeedsUseCase() {
            override fun getAllPosts(callback: Callback<List<Post>?>) {
                callback.onResult(TestObjects.getPostList())
            }
        })
        assertFalse(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
    }

    @Test
    fun test_data_loaded() {
        viewModelUnderTest = FeedsFragmentViewModel(object : FeedsUseCase() {
            override fun getAllPosts(callback: Callback<List<Post>?>) {
                callback.onResult(TestObjects.getPostList())
            }
        })
        viewModelUnderTest.loadPosts()
        assertFalse(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
        assertFalse(viewModelUnderTest.postsLiveData.value!!.isEmpty())
        assertTrue(viewModelUnderTest.postsLiveData.value!!.size == TestObjects.getPostList().size)
    }

    @Test
    fun test_data_load_error(){
        viewModelUnderTest = FeedsFragmentViewModel(object : FeedsUseCase() {
            override fun getAllPosts(callback: Callback<List<Post>?>) {
                callback.onFailure(Throwable())
            }
        })
        viewModelUnderTest.loadPosts()
        assertFalse(viewModelUnderTest.loadingInProgress)
        assertTrue(viewModelUnderTest.showReloadButton)
    }

    @Test
    fun test_loading_state(){
        viewModelUnderTest = FeedsFragmentViewModel(object : FeedsUseCase() {
            override fun getAllPosts(callback: Callback<List<Post>?>) {
            }
        })
        viewModelUnderTest.loadPosts()
        assertTrue(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
    }
}
