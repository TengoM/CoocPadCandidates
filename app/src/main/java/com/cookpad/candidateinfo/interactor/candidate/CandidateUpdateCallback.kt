package com.cookpad.candidateinfo.interactor.candidate

import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface CandidateUpdateCallback {
    fun onCandidateUpdated(candidate: CandidateListItem.Info)
    fun onCandidateAdded(candidate: CandidateListItem.Info)
}