package io.jonibek.feedster.data.datasource.user.remote

import io.jonibek.feedster.data.entities.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface UserAPI {

    @GET("users/{id}")
    fun getUserById(@Path("id") userId: Int): Single<User>

}