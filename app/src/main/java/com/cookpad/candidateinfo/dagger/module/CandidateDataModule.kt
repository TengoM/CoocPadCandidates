package com.cookpad.candidateinfo.dagger.module

import com.cookpad.appdata.CandidateRepository
import com.cookpad.appdata.RealmInteractor
import com.cookpad.candidateinfo.interactor.candidate.CandidateInteractor
import com.cookpad.candidateinfo.interactor.candidate.CandidateInteractorImpl
import com.cookpad.candidateinfo.interactor.validation.CandidateValidation
import com.cookpad.candidateinfo.interactor.validation.CandidateValidationImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CandidateDataModule {
    @Provides
    @Singleton
    fun provideCandidateRepository(): CandidateRepository = RealmInteractor()

    @Provides
    @Singleton
    fun provideCandidateInteractor(candidateRepository: CandidateRepository)
            : CandidateInteractor = CandidateInteractorImpl(candidateRepository)

    @Provides
    @Singleton
    fun provideCandidateValidation(): CandidateValidation = CandidateValidationImpl()

}