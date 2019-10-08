package io.jonibek.feedster.data.datasource.user.local

import android.graphics.drawable.Drawable

interface UserLocalDataSource {

    fun getAvatar(userId: Int): Drawable

}