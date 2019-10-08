package io.jonibek.feedster.data.repository.user

import io.jonibek.feedster.data.entities.User
import io.reactivex.Single

interface UserRepository {

    fun getUserById(userId: Int): Single<User>

}
