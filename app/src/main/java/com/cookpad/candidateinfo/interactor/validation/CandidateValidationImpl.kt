package com.cookpad.candidateinfo.interactor.validation

import com.cookpad.candidateinfo.R
import com.cookpad.candidateinfo.constants.Constants
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

class CandidateValidationImpl: CandidateValidation{

    override fun checkIfCandidateInfoIsValid(candidate: CandidateListItem.Info, callback: (isValid: Boolean, displayTextResId: Int) -> Unit) {
        var displayTextResId = R.string.success
        var isSuccess = true

        if(!isNameValid(candidate.name)) {
            displayTextResId = R.string.invalid_candidate_name
            isSuccess = false
        }else if(!isMailValid(candidate.email)){
            displayTextResId = R.string.invalid_mail
            isSuccess = false
        }else if(!isPhoneValid(candidate.phoneNumber)){
            displayTextResId = R.string.invalid_phone_number
            isSuccess = false
        }else if(!isGradeValid(candidate.assessmentGrade)){
            displayTextResId = R.string.invalid_grade
            isSuccess = false
        }
        callback(isSuccess, displayTextResId)
    }

    private fun isGradeValid(assessmentGrade: String): Boolean = Constants.CANDIDATE_GRADES.contains(assessmentGrade)

    private fun isPhoneValid(phoneNumber: String): Boolean {
        return phoneNumber.isNotEmpty()
    }

    private fun isNameValid(name: String): Boolean = name.isNotEmpty()

    private fun isMailValid(mail: String): Boolean = mail.contains("@") && mail.length > 5

}