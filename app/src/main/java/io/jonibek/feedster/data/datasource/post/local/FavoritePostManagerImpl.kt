package io.jonibek.feedster.data.datasource.post.local

import android.content.SharedPreferences
import javax.inject.Inject


class FavoritePostManagerImpl
@Inject
constructor(private val sharedPreferences: SharedPreferences) : FavoritePostManager {

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