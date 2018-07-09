package com.cookpad.candidateinfo

import android.app.Application
import com.cookpad.candidateinfo.dagger.component.AppComponent
import com.cookpad.candidateinfo.dagger.component.DaggerAppComponent
import com.cookpad.candidateinfo.dagger.module.AppModule
import com.cookpad.candidateinfo.dagger.module.CandidateDataModule
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        configureRealm()
        initAppComponent()
    }

    private fun configureRealm(){
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun initAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .candidateDataModule(CandidateDataModule())
                .build()

    }
}