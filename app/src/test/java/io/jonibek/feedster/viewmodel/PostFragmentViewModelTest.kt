package io.jonibek.feedster.viewmodel

import io.jonibek.TestObjects
import io.jonibek.feedster.data.pojo.Comment
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.data.pojo.User
import io.jonibek.feedster.domain.post.PostUseCase
import io.jonibek.feedster.ui.post.PostFragmentViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PostFragmentViewModelTest : BaseViewModelTest() {

    lateinit var viewModelUnderTest: PostFragmentViewModel

    private fun test_pre_load_view_model_state() {
        viewModelUnderTest.postId = 1
        assertTrue(viewModelUnderTest.commentsCount == "")
        assertFalse(viewModelUnderTest.commentSectionVisiblity)
        assertTrue(viewModelUnderTest.postBody == "")
        assertTrue(viewModelUnderTest.postTitle == "")
        assertTrue(viewModelUnderTest.postAuthor == "")
        assertFalse(viewModelUnderTest.commentsSectionExpended)
        assertFalse(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
    }

    @Test
    fun test_post_fields(){
        val post = TestObjects.getPost()
        viewModelUnderTest = PostFragmentViewModel(object : PostUseCase() {
            override fun getPostById(postId: Int, callback: Callback<Post>) {
                callback.onResult(post)
            }

            override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
                callback.onFailure(Throwable())
            }

            override fun getUserById(userId: Int, callback: Callback<User>) {
                callback.onFailure(Throwable())
            }
        })
        test_pre_load_view_model_state()
        viewModelUnderTest.loadData()
        assertTrue(viewModelUnderTest.postTitle == post.title)
        assertTrue(viewModelUnderTest.postBody == post.body)
        assertFalse(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
        assertFalse(viewModelUnderTest.commentSectionVisiblity)
        assertTrue(viewModelUnderTest.commentsCount == "")
    }

    @Test
    fun test_post_error(){
        viewModelUnderTest = PostFragmentViewModel(object : PostUseCase() {
            override fun getPostById(postId: Int, callback: Callback<Post>) {
                callback.onFailure(Throwable())
            }

            override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
            }

            override fun getUserById(userId: Int, callback: Callback<User>) {
            }
        })
        test_pre_load_view_model_state()
        viewModelUnderTest.loadData()
        assertTrue(viewModelUnderTest.showReloadButton)
        assertFalse(viewModelUnderTest.loadingInProgress)
    }

    @Test
    fun test_user_fields(){
        val user = TestObjects.getUser()
        val post = TestObjects.getPost()
        viewModelUnderTest = PostFragmentViewModel(object : PostUseCase() {
            override fun getPostById(postId: Int, callback: Callback<Post>) {
                callback.onResult(post)
            }

            override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
            }

            override fun getUserById(userId: Int, callback: Callback<User>) {
                callback.onResult(user)
            }
        })
        test_pre_load_view_model_state()
        viewModelUnderTest.loadData()
        assertTrue(viewModelUnderTest.postAuthor == user.username)
    }

    @Test
    fun test_comment_fields(){
        val comments = TestObjects.getComments()
        viewModelUnderTest = PostFragmentViewModel(object  : PostUseCase() {
            override fun getPostById(postId: Int, callback: Callback<Post>) {

            }

            override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
                callback.onResult(comments)
            }

            override fun getUserById(userId: Int, callback: Callback<User>) {

            }
        })
        test_pre_load_view_model_state()
        viewModelUnderTest.loadData()
        assertTrue(viewModelUnderTest.commentsCount == "(${comments.size})")
        assertTrue(viewModelUnderTest.commentSectionVisiblity)
        assertFalse(viewModelUnderTest.commentsSectionExpended)
        viewModelUnderTest.expendComments()
        assertTrue(viewModelUnderTest.commentsSectionExpended)
    }

    @Test
    fun test_loading_state(){
        viewModelUnderTest = PostFragmentViewModel(object  : PostUseCase(){
            override fun getPostById(postId: Int, callback: Callback<Post>) {
            }

            override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
            }

            override fun getUserById(userId: Int, callback: Callback<User>) {
            }
        })
        test_pre_load_view_model_state()
        viewModelUnderTest.loadData()
        assertTrue(viewModelUnderTest.commentsCount == "")
        assertFalse(viewModelUnderTest.commentSectionVisiblity)
        assertTrue(viewModelUnderTest.postBody == "")
        assertTrue(viewModelUnderTest.postTitle == "")
        assertTrue(viewModelUnderTest.postAuthor == "")
        assertFalse(viewModelUnderTest.commentsSectionExpended)
        assertTrue(viewModelUnderTest.loadingInProgress)
        assertFalse(viewModelUnderTest.showReloadButton)
    }
}
