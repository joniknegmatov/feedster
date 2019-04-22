package io.jonibek.feedster.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("address") val address: Address,
    @SerializedName("phone") val phone: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("company") val company: Company
)