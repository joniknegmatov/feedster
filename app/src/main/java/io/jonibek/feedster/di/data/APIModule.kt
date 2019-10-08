package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.remote.CommentAPI
import io.jonibek.feedster.data.datasource.post.remote.PostAPI
import io.jonibek.feedster.data.datasource.user.remote.UserAPI
import retrofit2.Retrofit

@Module
class APIModule {

    @Provides
    fun providesCommentAPI(retrofit: Retrofit) : CommentAPI = retrofit.create(CommentAPI::class.java)

    @Provides
    fun providesPostAPI(retrofit: Retrofit) : PostAPI = retrofit.create(PostAPI::class.java)

    @Provides
    fun providesUserAPI(retrofit: Retrofit) : UserAPI = retrofit.create(UserAPI::class.java)

}