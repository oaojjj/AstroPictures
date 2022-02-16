package com.oseong.ifeelalive

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return "${element.fileName}(${element.lineNumber})#${element.methodName}"
                }
            })
        }
    }
}