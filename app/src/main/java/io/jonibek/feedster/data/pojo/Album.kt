package io.jonibek.feedster.data.pojo

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String
)