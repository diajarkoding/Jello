package com.iskan.domain.model.ui

data class JelloHomeUiModel(
    val body: List<BodyUiModel> = listOf(),
    val header: List<HeaderUiModel> = listOf()
) {
    data class BodyUiModel(
        val data: List<DataUiModel> = listOf(),
        val title: String = ""
    ) {
        data class DataUiModel(
            val id: Int = 0,
            val image: String = ""
        )
    }

    data class HeaderUiModel(
        val id: Int = 0,
        val image: String = ""
    )
}