package com.android.petsdetails.misc

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}