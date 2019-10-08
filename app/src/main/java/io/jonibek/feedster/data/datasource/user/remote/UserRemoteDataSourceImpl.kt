package io.jonibek.feedster.data.datasource.user.remote

import javax.inject.Inject

class UserRemoteDataSourceImpl
@Inject
constructor(private val userAPI: UserAPI) : UserRemoteDataSource {

    override  fun getUserById(userId: Int) = userAPI.getUserById(userId)

}