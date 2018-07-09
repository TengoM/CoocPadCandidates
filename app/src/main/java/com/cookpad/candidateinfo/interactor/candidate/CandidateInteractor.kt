package com.cookpad.candidateinfo.interactor.candidate

import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface CandidateInteractor {
    fun getAllCandidates(): List<CandidateListItem.Info>
    fun getCandidateById(id: Int): CandidateListItem.Info?
    fun updateCandidate(candidate: CandidateListItem.Info)
    fun addCandidate(candidate: CandidateListItem.Info)
    fun deleteCandidateWithId(id: Int)
    fun getCandidateCount(): Int
    fun registerCandidateUpdateListener(callback: CandidateUpdateCallback)
}