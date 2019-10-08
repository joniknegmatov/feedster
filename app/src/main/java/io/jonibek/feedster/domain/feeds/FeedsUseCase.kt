package io.jonibek.feedster.domain.feeds

import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single

interface FeedsUseCase {

    fun getAllPosts() : Single<List<Post>>

}