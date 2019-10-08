package io.jonibek.feedster.data.datasource.post.local

interface FavoritePostManager {

    fun getListOfFavorites(): MutableSet<String>?

    fun saveListOfFavorites(favoritesList: MutableSet<String>)

}