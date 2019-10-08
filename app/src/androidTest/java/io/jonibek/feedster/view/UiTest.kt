package io.jonibek.feedster.view

import android.content.Intent
import android.support.test.espresso.Espresso.onIdle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.jonibek.feedster.R
import io.jonibek.feedster.TestObjects
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import io.jonibek.feedster.di.TestInjector
import io.jonibek.feedster.di.TestRemoteDataSourceModule
import io.jonibek.feedster.presentation.MainActivity
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class UiTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, true,  false)

    private val comments = TestObjects.getComments()
    private val posts = TestObjects.getPostList()
    private val post = TestObjects.getPost()
    private val user = TestObjects.getUser()

    @Mock
    lateinit var commentRemoteDataSource: CommentRemoteDataSource

    @Mock
    lateinit var postRemoteDataSource: PostRemoteDataSource

    @Mock
    lateinit var userRemoteDataSource: UserRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(commentRemoteDataSource.getCommentsByPostId(0)).then { Single.just(comments) }
        Mockito.`when`(postRemoteDataSource.getPostById(0)).then { Single.just(post) }
        Mockito.`when`(postRemoteDataSource.getAllPosts()).then { Single.just(posts) }
        Mockito.`when`(postRemoteDataSource.getPostsByUserId(0)).then { Single.just(posts) }
        Mockito.`when`(userRemoteDataSource.getUserById(0)).then { Single.just(user) }
        TestInjector(
            TestRemoteDataSourceModule(
                commentRemoteDataSource,
                postRemoteDataSource,
                userRemoteDataSource
            )
        ).inject()
    }

    @Test
    fun test() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.rvPosts)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onIdle()
    }

}
