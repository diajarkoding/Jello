package com.iskan.jello.di.chucker

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.RetentionManager

class ChuckerCrashHandler (
            private val defaultHandler: Thread.UncaughtExceptionHandler? = null,
            private val applicationContext: Context
) : Thread.UncaughtExceptionHandler {

    private val chuckerCollector : ChuckerCollector by lazy {
        ChuckerCollector(
            applicationContext,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.FOREVER
            )
    }
    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        chuckerCollector.onError("error",throwable)
        defaultHandler?.uncaughtException(thread, throwable)
    }

}


