package io.jonibek.feedster.di.features

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.domain.post.PostUseCase
import io.jonibek.feedster.domain.post.PostUseCaseImpl

@Module
class PostFragmentModule {

    @Provides
    fun providesFeedsUseCase(postUseCaseImpl: PostUseCaseImpl): PostUseCase = postUseCaseImpl

}
