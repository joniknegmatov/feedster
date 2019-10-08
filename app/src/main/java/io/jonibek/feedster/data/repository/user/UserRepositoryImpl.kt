package io.jonibek.feedster.data.repository.user

import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import javax.inject.Inject


class UserRepositoryImpl
@Inject
constructor(private val userRemoteDataSource: UserRemoteDataSource) :
    UserRepository {

    override fun getUserById(userId: Int) = userRemoteDataSource.getUserById(userId)

}