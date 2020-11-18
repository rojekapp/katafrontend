package com.paimon.katahack

import android.app.Application
import com.paimon.katahack.util.module
import io.realm.BuildConfig
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class BaseApp : Application() {

    companion object {

        private lateinit var instance: BaseApp

        fun getInstance(): BaseApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name("katahack.realm").build()
        Realm.setDefaultConfiguration(config)
        initKoin()

    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(module)
        )
    }

}