package com.iskan.domain.model.base

sealed class UiState<V> {
    class OnLoading<V> : UiState<V>()
    data class OnSuccess<V : Any>(val data: V?) : UiState<V>()
    data class OnFailed<V>(
        val message: String? = null,
        val domainResourceError: DomainResourceError? = null
    ) : UiState<V>()

}