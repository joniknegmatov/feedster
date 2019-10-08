package io.jonibek.feedster.data.repository.post

import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postLocalDataSource: PostLocalDataSource
) : PostRepository {

    override fun isPostInFavorites(postId: Int) = postLocalDataSource.isPostInFavorites(postId)

    override fun changeFavorite(postId: Int) = postLocalDataSource.changeFavorite(postId)

    override fun getPostById(postId: Int) = postRemoteDataSource.getPostById(postId)

    override fun getAllPosts() = postRemoteDataSource.getAllPosts()

    override fun getPostsByUserId(userId: Int) = postRemoteDataSource.getPostsByUserId(userId)

}