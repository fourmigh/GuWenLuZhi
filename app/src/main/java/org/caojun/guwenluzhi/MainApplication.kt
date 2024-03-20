package org.caojun.guwenluzhi

import android.app.Application
import org.caojun.guwenluzhi.realm.RealmManager

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RealmManager.getInstance().init()
    }
}