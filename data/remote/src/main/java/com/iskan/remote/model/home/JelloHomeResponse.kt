package com.iskan.remote.model.home


import com.google.gson.annotations.SerializedName

data class JelloHomeResponse(
    @SerializedName("body")
    val body: List<BodyResponse>?,
    @SerializedName("header")
    val header: List<HeaderResponse>?
) {
    data class BodyResponse(
        @SerializedName("data")
        val data: List<DataResponse>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class DataResponse(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("image")
            val image: String?
        )
    }

    data class HeaderResponse(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: String?
    )
}