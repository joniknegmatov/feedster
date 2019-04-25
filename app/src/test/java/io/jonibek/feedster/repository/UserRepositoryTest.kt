package io.jonibek.feedster.repository

import io.jonibek.feedster.TestObjects
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class UserRepositoryTest {

    private val userRemoteDataSource = Mockito.mock(UserRemoteDataSource::class.java)
    private lateinit var userRepository: UserRepository
    private val userSingle = Single.just(TestObjects.getUser())
    @Test
    fun test_repository_fields(){
        Mockito.`when`(userRemoteDataSource.getUserById(1)).then{ userSingle }
        userRepository = UserRepository.UserRepositoryImpl(userRemoteDataSource)
        assertTrue(userRepository.getUserById(1) == userSingle)
    }


}