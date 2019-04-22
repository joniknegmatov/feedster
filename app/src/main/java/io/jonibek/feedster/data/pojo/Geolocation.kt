package io.jonibek.feedster.data.pojo

import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat") val lat: Float,
    @SerializedName("lng") val lng: Float
)
