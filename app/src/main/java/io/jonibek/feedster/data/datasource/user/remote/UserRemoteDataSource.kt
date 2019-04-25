package io.jonibek.feedster.data.datasource.user.remote

import io.jonibek.feedster.data.entities.User
import io.reactivex.Single
import javax.inject.Inject

interface UserRemoteDataSource {

    fun getUserById(userId: Int): Single<User>

    class UserRemoteDataSourceImpl @Inject constructor(private val userApi: UserApi) : UserRemoteDataSource {

       override  fun getUserById(userId: Int) = userApi.getUserById(userId)

    }
}
