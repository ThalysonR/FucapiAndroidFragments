package br.com.tcastro.trabalho2

import REALM_DB_NAME
import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}