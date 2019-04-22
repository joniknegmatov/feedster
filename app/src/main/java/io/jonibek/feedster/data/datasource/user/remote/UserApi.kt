package io.jonibek.feedster.data.datasource.user.remote

import io.jonibek.feedster.data.entities.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface UserApi {

    @GET("users/{id}")
    fun getUserById(@Path("id") userId: Int): Single<User>

    class UserApiImpl @Inject constructor(retrofit: Retrofit) : UserApi {

        private val userApi by lazy { retrofit.create(UserApi::class.java) }

        override fun getUserById(userId: Int) = userApi.getUserById(userId)

    }

}