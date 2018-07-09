package com.cookpad.candidateinfo.dagger.component

import com.cookpad.candidateinfo.dagger.module.AppModule
import com.cookpad.candidateinfo.dagger.module.CandidateDataModule
import com.cookpad.candidateinfo.presenter.CandidateDetailPresenterImpl
import com.cookpad.candidateinfo.presenter.MainPresenterImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        CandidateDataModule::class
))
open interface AppComponent {
    fun inject(mainPresenterImpl: MainPresenterImpl)
    fun inject(mainPresenterImpl: CandidateDetailPresenterImpl)
}