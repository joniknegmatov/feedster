package io.jonibek.feedster.data.datasource.user.local

import android.content.res.Resources
import android.graphics.drawable.Drawable
import io.jonibek.feedster.R
import javax.inject.Inject

class UserLocalDataSourceImpl
@Inject
constructor(private val resources: Resources) : UserLocalDataSource {

    override fun getAvatar(userId: Int): Drawable {
        return resources.getDrawable(R.drawable.ic_avatar_girl, resources.newTheme())
    }

}