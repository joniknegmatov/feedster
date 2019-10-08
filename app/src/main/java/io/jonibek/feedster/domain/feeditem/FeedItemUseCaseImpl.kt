package io.jonibek.feedster.domain.feeditem

import io.jonibek.feedster.data.repository.post.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class FeedItemUseCaseImpl
@Inject
constructor(private val postRepository: PostRepository) : FeedItemUseCase {

    override fun isPostFavorite(postId: Int): Single<Boolean> {
         return postRepository.isPostInFavorites(postId)
    }

    override fun changeFavorite(postId: Int): Single<Boolean> {
        return postRepository.changeFavorite(postId)
    }

}