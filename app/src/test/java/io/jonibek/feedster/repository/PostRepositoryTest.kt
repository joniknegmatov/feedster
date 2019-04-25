package io.jonibek.feedster.repository

import io.jonibek.feedster.TestObjects
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class PostRepositoryTest {

    private val postRemoteDataSource = Mockito.mock(PostRemoteDataSource::class.java)
    private val postLocalDataSource = Mockito.mock(PostLocalDataSource::class.java)
    private lateinit var postRepositoryTest: PostRepository
    private val postsListSingle = Single.just(TestObjects.getPostList())
    private val postSingle = Single.just(TestObjects.getPost())
    private val booleanSingle = Single.just(true)

    @Test
    fun test_repository_fields() {
        Mockito.`when`(postRemoteDataSource.getAllPosts()).then { postsListSingle }
        Mockito.`when`(postRemoteDataSource.getPostsByUserId(1)).then { postsListSingle }
        Mockito.`when`(postRemoteDataSource.getPostById(1)).then { postSingle }
        Mockito.`when`(postRemoteDataSource.getPostById(1)).then { postSingle }
        Mockito.`when`(postRemoteDataSource.getPostById(1)).then { postSingle }
        Mockito.`when`(postLocalDataSource.isPostInFavorites(1)).then { booleanSingle }
        Mockito.`when`(postLocalDataSource.changeFavorite(1)).then { booleanSingle }

        postRepositoryTest = PostRepository.PostRepositoryImpl(postRemoteDataSource, postLocalDataSource)

        assertTrue(postRepositoryTest.getAllPosts() == postsListSingle)
        assertTrue(postRepositoryTest.getPostById(1) == postSingle)
        assertTrue(postRepositoryTest.getPostsByUserId(1) == postsListSingle)
        assertTrue(postRepositoryTest.changeFavorite(1) == booleanSingle)
        assertTrue(postRepositoryTest.isPostInFavorites(1) == booleanSingle)
    }

}