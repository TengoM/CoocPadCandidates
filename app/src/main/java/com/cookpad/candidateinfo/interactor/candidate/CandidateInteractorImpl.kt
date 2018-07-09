package com.cookpad.candidateinfo.interactor.candidate

import com.cookpad.appdata.CandidateChangeCallback
import com.cookpad.appdata.CandidateRepository
import com.cookpad.appdata.models.Candidate
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

open class CandidateInteractorImpl(private val candidateRepository: CandidateRepository) : CandidateInteractor {

    override fun getAllCandidates(): List<CandidateListItem.Info> =
            candidateRepository.getAllCandidates().map { convertDbIntoViewModel(it)!! }

    override fun getCandidateById(id: Int): CandidateListItem.Info? =
            convertDbIntoViewModel(
                    candidateRepository.getCandidateById(id)
            )

    override fun updateCandidate(candidate: CandidateListItem.Info) {
        candidateRepository.updateCandidate(
                convertViewIntoDbModel(candidate)
        )
    }

    override fun addCandidate(candidate: CandidateListItem.Info) {
        candidateRepository.addCandidate(
                convertViewIntoDbModel(candidate)
        )
    }

    override fun deleteCandidateWithId(id: Int) = candidateRepository.deleteCandidateWithId(id)

    override fun getCandidateCount(): Int = candidateRepository.getCandidateCount()

    override fun registerCandidateUpdateListener(callback: CandidateUpdateCallback) {
        candidateRepository.addListChangeCallback(
                object: CandidateChangeCallback{
                    override fun itemsChanged(list: List<Candidate>) {
                        list.forEach{
                            callback.onCandidateUpdated(convertDbIntoViewModel(it)!!)
                        }
                    }

                    override fun itemsAdded(list: List<Candidate>) {
                        list.forEach{
                            callback.onCandidateAdded(convertDbIntoViewModel(it)!!)
                        }
                    }
                }
        )
    }


    fun convertDbIntoViewModel(it: Candidate?): CandidateListItem.Info?{
        return if(it == null){
            null
        }else {
            CandidateListItem.Info(it.candidateId, it.candidateName, it.email, it.phoneNumber, it.assessmentGrade)
        }
    }

    fun convertViewIntoDbModel(it: CandidateListItem.Info): Candidate =
            Candidate(it.id, it.email, it.phoneNumber, it.name, it.assessmentGrade)
}