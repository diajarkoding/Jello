package com.iskan.domain.mapper.base

interface Mapper<T, E> {
    fun to(t: T): E
}