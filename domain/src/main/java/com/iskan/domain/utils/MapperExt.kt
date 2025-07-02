package com.iskan.domain.utils

import com.iskan.domain.mapper.base.Mapper

fun <A, B> A.mapTo(mapper: Mapper<A, B>) :B {
    return mapper.to(this)
}