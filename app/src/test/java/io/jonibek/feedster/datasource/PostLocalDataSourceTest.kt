package io.jonibek.feedster.datasource

import io.jonibek.feedster.data.datasource.post.local.FavoritePostManager
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PostLocalDataSourceTest {

    lateinit var postLocalDataSourceUnderTest: PostLocalDataSource

    private val favoritePostManager = object : FavoritePostManager {

        var list = mutableSetOf<String>()

        override fun getListOfFavorites(): MutableSet<String>? {
            return HashSet(list)

        }

        override fun saveListOfFavorites(favoritesList: MutableSet<String>) {
            list = favoritesList
        }

    }

    @Before
    fun init() {
        postLocalDataSourceUnderTest = PostLocalDataSource.PostLocalDataSourceImpl(favoritePostManager)
    }


    @Test
    fun test_adding_to_favorites_and_removing() {
        assertFalse(postLocalDataSourceUnderTest.isPostInFavorites(1).blockingGet())
        assertTrue(postLocalDataSourceUnderTest.changeFavorite(1).blockingGet())
        assertTrue(postLocalDataSourceUnderTest.isPostInFavorites(1).blockingGet())
        assertFalse(postLocalDataSourceUnderTest.changeFavorite(1).blockingGet())
        assertFalse(postLocalDataSourceUnderTest.isPostInFavorites(1).blockingGet())

    }

}