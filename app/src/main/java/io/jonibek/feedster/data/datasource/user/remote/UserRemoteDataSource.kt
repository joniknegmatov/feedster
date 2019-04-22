package io.jonibek.feedster.data.datasource.user.remote

import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.data.pojo.User
import io.reactivex.Single
import javax.inject.Inject

interface UserRemoteDataSource {

    fun getUserById(userId: Int): Single<User>

    class UserRemoteDataSourceImpl @Inject constructor(val userApi: UserApi) : UserRemoteDataSource {

       override  fun getUserById(userId: Int) = userApi.getUserById(userId)

    }
}
