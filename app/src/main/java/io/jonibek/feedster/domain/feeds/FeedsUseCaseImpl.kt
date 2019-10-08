package io.jonibek.feedster.domain.feeds

import io.jonibek.feedster.data.repository.post.PostRepository
import io.jonibek.feedster.data.entities.Post
import io.reactivex.Single
import javax.inject.Inject


class FeedsUseCaseImpl
@Inject
constructor(private val postRepository: PostRepository) : FeedsUseCase {

    override fun getAllPosts() : Single<List<Post>> {
        return postRepository.getAllPosts()
    }

}