package com.iskan.jello.annotation
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkHttpInstance {}