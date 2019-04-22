package io.jonibek.feedster.data.datasource.user

import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import io.jonibek.feedster.data.entities.User
import io.reactivex.Single
import javax.inject.Inject

interface UserRepository {

    fun getUserById(userId: Int): Single<User>

    class UserRepositoryImpl @Inject constructor(val userRemoteDataSource: UserRemoteDataSource) : UserRepository {

        override fun getUserById(userId: Int) = userRemoteDataSource.getUserById(userId)
    }
}
