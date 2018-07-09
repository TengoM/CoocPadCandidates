package com.cookpad.candidateinfo.dagger.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication() = mApplication


}
