package com.iskan.domain.model.base

sealed class DomainResource<out T> {

    data class OnSuccess<T>(val data: T) : DomainResource<T>()

    data object OnSuccessNoData : DomainResource<Nothing>()

    data class OnError(val domainResourceError: DomainResourceError) : DomainResource<Nothing>()

}