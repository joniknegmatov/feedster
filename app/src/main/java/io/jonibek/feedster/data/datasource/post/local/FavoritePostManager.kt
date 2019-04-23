package io.jonibek.feedster.data.datasource.post.local

import android.content.SharedPreferences

interface FavoritePostManager {

    fun getListOfFavorites(): MutableSet<String>?

    fun saveListOfFavorites(favoritesList: MutableSet<String>)

    class FavoritePostManagerImpl(private val sharedPreferences: SharedPreferences) : FavoritePostManager {

        override fun getListOfFavorites(): MutableSet<String>? {
            return sharedPreferences.getStringSet(LIKED_POSTS_LIST, mutableSetOf())
        }

        override fun saveListOfFavorites(favoritesList: MutableSet<String>) {
            sharedPreferences.edit().putStringSet(LIKED_POSTS_LIST, favoritesList).apply()
        }


        companion object {
            const val LIKED_POSTS_LIST = "LIKED_POSTS"
        }

    }
}