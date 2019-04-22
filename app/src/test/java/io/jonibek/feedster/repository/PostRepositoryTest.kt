package io.jonibek.feedster.repository

import io.jonibek.TestObjects
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class PostRepositoryTest {

    private val postRemoteDataSource = Mockito.mock(PostRemoteDataSource::class.java)
    private lateinit var postRepositoryTest: PostRepository
    private val postsListSingle = Single.just(TestObjects.getPostList())
    private val postSingle = Single.just(TestObjects.getPost())

    @Test
    fun test_repository_fields(){
        Mockito.`when`(postRemoteDataSource.getAllPosts()).then{ postsListSingle }
        Mockito.`when`(postRemoteDataSource.getPostsByUserId(1)).then{ postsListSingle }
        Mockito.`when`(postRemoteDataSource.getPostById(1)).then{ postSingle }
        postRepositoryTest = PostRepository.PostRepositoryImpl(postRemoteDataSource)
        assertTrue(postRepositoryTest.getAllPosts() == postsListSingle)
        assertTrue(postRepositoryTest.getPostById(1) == postSingle)
        assertTrue(postRepositoryTest.getPostsByUserId(1) == postsListSingle)
    }

}