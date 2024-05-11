package com.binayshaw7777.knowtify

import android.app.Application
import co.touchlab.kermit.Logger
import util.ApplicationComponent

class Knowtify : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.d("KnowtifyApplication")
        ApplicationComponent.init()
    }
}