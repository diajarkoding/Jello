package com.iskan.domain.model.domain

data class JelloHomeDomainModel(
    val body: List<BodyDomainModel> = listOf(),
    val header: List<HeaderDomainModel> = listOf()
) {
    data class BodyDomainModel(
        val data: List<DataDomainModel> = listOf(),
        val title: String = ""
    ) {
        data class DataDomainModel(
            val id: Int = 0,
            val image: String = ""
        )
    }

    data class HeaderDomainModel(
        val id: Int = 0,
        val image: String = ""
    )
}