package io.jonibek.feedster.di.features

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.domain.post.PostUseCase
import io.jonibek.feedster.ui.post.PostFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class PostFragmentModule {

    @Provides
    fun providesFeedsUseCase(
        postRepository: PostRepository,
        commentRepository: CommentRepository,
        userRepository: UserRepository
    ): PostUseCase {
        return PostUseCase.PostUseCaseImpl(
            postRepository,
            commentRepository,
            userRepository,
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }

    @Provides
    fun providesFeedsViewModelFactory(postUseCase: PostUseCase): PostFragmentViewModel.Factory {
        return PostFragmentViewModel.Factory(postUseCase)
    }
}
