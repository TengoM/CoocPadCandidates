package com.cookpad.candidateinfo.ui.activity.candidate

import com.cookpad.candidateinfo.ui.base.BasePresenter

interface CandidateDetailPresenter: BasePresenter {
    fun onSubmitClicked()
    fun onGradeClicked()
}