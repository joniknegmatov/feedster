package io.jonibek.feedster.data

import io.jonibek.feedster.data.datasource.post.local.FavoritePostManager

class TestFavoritePostManager : FavoritePostManager {

    private var testList: MutableSet<String> = mutableSetOf()

    override fun getListOfFavorites(): MutableSet<String>? {
        return testList
    }

    override fun saveListOfFavorites(favoritesList: MutableSet<String>) {
        testList = favoritesList
    }

}