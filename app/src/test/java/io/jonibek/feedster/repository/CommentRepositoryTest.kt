package io.jonibek.feedster.repository

import io.jonibek.TestObjects
import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class CommentRepositoryTest {

    private val commentRemoteDataSource = Mockito.mock(CommentRemoteDataSource::class.java)
    private lateinit var commentRepository: CommentRepository
    private val commentsSingle = Single.just(TestObjects.getComments())

    @Test
    fun test_repository_fields(){
        Mockito.`when`(commentRemoteDataSource.getCommentsByPostId(1)).then{ commentsSingle }
        commentRepository = CommentRepository.CommentRepositoryImpl(commentRemoteDataSource)
        assertTrue(commentRepository.getCommentsByPostId(1) == commentsSingle)
    }


}