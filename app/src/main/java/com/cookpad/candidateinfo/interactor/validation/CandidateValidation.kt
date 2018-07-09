package com.cookpad.candidateinfo.interactor.validation

import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface CandidateValidation {
    fun checkIfCandidateInfoIsValid(candidate: CandidateListItem.Info, callback: (isValid: Boolean, displayTextResId: Int) -> Unit)
}