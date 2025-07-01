package com.iskan.remote.model.signin

import com.google.gson.annotations.SerializedName

data class JelloSignInResponse(
    @SerializedName("address")
    val address: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profil")
    val profil: String?,
    @SerializedName("token")
    val token: String?
)