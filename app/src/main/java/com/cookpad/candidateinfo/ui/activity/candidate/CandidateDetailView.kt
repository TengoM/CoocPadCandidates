package com.cookpad.candidateinfo.ui.activity.candidate

import android.support.annotation.StringRes
import com.cookpad.candidateinfo.ui.base.BaseView
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface CandidateDetailView: BaseView {
    fun updateSubmitButtonText(@StringRes submitButtonName: Int)
    fun setCandidateListItemInfo(currentCandidate: CandidateListItem.Info)
    fun getPassedCandidateId(): Int?
    fun setupClickListeners()
    fun showToastFor(@StringRes textResId: Int)

    fun getNameText(): String
    fun getEmailText(): String
    fun getPhoneText(): String
    fun getGradeText(): String
    fun finishActivity()
    fun pushGradeChooseDialogForGrades(gradeList: List<String>)
}