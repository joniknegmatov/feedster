package io.jonibek.feedster.data.entities

import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat") val lat: Float,
    @SerializedName("lng") val lng: Float
)
