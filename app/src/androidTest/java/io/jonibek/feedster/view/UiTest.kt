package io.jonibek.feedster.view

import android.content.Intent
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.jonibek.feedster.R
import io.jonibek.feedster.TestObjects
import io.jonibek.feedster.di.TestRemoteDataSourceModule.Companion.commentRemoteDataSource
import io.jonibek.feedster.di.TestRemoteDataSourceModule.Companion.postRemoteDataSource
import io.jonibek.feedster.di.TestRemoteDataSourceModule.Companion.userRemoteDataSource
import io.jonibek.feedster.ui.MainActivity
import io.reactivex.Single
import org.hamcrest.Matchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class UiTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    val comments = TestObjects.getComments()
    val posts = TestObjects.getPostList()
    val post = TestObjects.getPost()
    val user = TestObjects.getUser()

    @Before
    fun init() {
        Mockito.`when`(commentRemoteDataSource.getCommentsByPostId(0)).then { Single.just(comments) }
        Mockito.`when`(postRemoteDataSource.getPostById(0)).then { Single.just(post) }
        Mockito.`when`(postRemoteDataSource.getAllPosts()).then { Single.just(posts) }
        Mockito.`when`(postRemoteDataSource.getPostsByUserId(0)).then { Single.just(posts) }
        Mockito.`when`(userRemoteDataSource.getUserById(0)).then { Single.just(user) }
        activityRule.launchActivity(Intent())
    }

    @Test
    fun test() {
        onView(withId(R.id.rvPosts)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onData(anything()).inAdapterView(withId(R.id.rvPosts)).atPosition(0).perform(click())
        onIdle()

    }

}
