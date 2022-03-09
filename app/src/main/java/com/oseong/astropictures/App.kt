package com.oseong.astropictures

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupActivityLifecycleLogging()
        setupTimber()
    }

    private fun setupActivityLifecycleLogging() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.d(activity.localClassName)
            }

            override fun onActivityStarted(activity: Activity) {
                Timber.d(activity.localClassName)
            }

            override fun onActivityResumed(activity: Activity) {
                Timber.d(activity.localClassName)
            }

            override fun onActivityPaused(activity: Activity) {
                Timber.d(activity.localClassName)
            }

            override fun onActivityStopped(activity: Activity) {
                Timber.d(activity.localClassName)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Timber.d(activity.localClassName)
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.d(activity.localClassName)
            }
            // Logging
        })
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