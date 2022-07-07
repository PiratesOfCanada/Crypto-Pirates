package com.example.cryptoanalysis

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HiltApp : Application(){
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object: Timber.DebugTree(){
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format("Class: ${super.createStackElementTag(element)}" +
                            " Line: ${element.lineNumber} Method: ${element.methodName}")
                }
            })

        } else {
            Timber.plant(ReleaseTree())
        }
    }
}