package io.jonibek.feedster.data.entities

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("albumId") val albumId : Int?,
    @SerializedName("id") val id : Int?,
    @SerializedName("url") val url : String?,
    @SerializedName("thumbnailUrl") val thumbnailUrl : String?
)