package com.cookpad.candidateinfo.presenter

import com.cookpad.candidateinfo.MyApp
import com.cookpad.candidateinfo.R
import com.cookpad.candidateinfo.constants.Constants
import com.cookpad.candidateinfo.interactor.candidate.CandidateInteractor
import com.cookpad.candidateinfo.interactor.validation.CandidateValidation
import com.cookpad.candidateinfo.ui.activity.candidate.CandidateDetailPresenter
import com.cookpad.candidateinfo.ui.activity.candidate.CandidateDetailView
import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import javax.inject.Inject

class CandidateDetailPresenterImpl(private val view: CandidateDetailView, myApp: MyApp) : CandidateDetailPresenter {

    init {
        myApp.appComponent.inject(this)
    }

    @Inject
    lateinit var candidateInteractor: CandidateInteractor

    @Inject
    lateinit var candidateValidation: CandidateValidation

    private lateinit var curMode: CurrentMode

    override fun onRenderView() {
        curMode = getCurrentMode()
        applyModeToView()
        setupClickListenerForView()
    }

    override fun onSubmitClicked() {
        val candidateInfo = getCandidateInfoFromView()
        candidateValidation.checkIfCandidateInfoIsValid(candidateInfo){
            isValid, displayTextResId ->
            view.showToastFor(displayTextResId)
            if(isValid){
                finishActivitySuccessfully(candidateInfo)
            }
        }
    }

    private fun finishActivitySuccessfully(candidateInfo: CandidateListItem.Info) {
        updateCandidateInfo(candidateInfo)
        view.finishActivity()
    }

    private fun updateCandidateInfo(candidateInfo: CandidateListItem.Info) {
        if(curMode == CurrentMode.ADDING){
            candidateInteractor.addCandidate(candidateInfo)
        }else{
            candidateInteractor.updateCandidate(candidateInfo)
        }
    }

    private fun getCandidateInfoFromView(): CandidateListItem.Info {
        val id = getPassedCandidateId() ?: -1
        val name = view.getNameText()
        val email = view.getEmailText()
        val phone = view.getPhoneText()
        val grade = view.getGradeText()
        return CandidateListItem.Info(id, name, email, phone, grade)
    }

    override fun onGradeClicked() {
        view.pushGradeChooseDialogForGrades(getGradeList())
    }

    private fun getGradeList() = Constants.CANDIDATE_GRADES

    private fun applyModeToView() {
        val submitButtonName = getSubmitButtonNameForCurMode()
        view.updateSubmitButtonText(submitButtonName)
        if (curMode == CurrentMode.EDITING) {
            applyEditingMode()
        }
    }

    private fun setupClickListenerForView() = view.setupClickListeners()

    private fun getCurrentMode(): CurrentMode {
        return if (getPassedCandidateId() == null)
            CurrentMode.ADDING
        else
            CurrentMode.EDITING
    }

    private fun getSubmitButtonNameForCurMode() =
            when (curMode) {
                CurrentMode.ADDING ->
                    R.string.add_user
                else ->
                    R.string.edit_user
            }

    private fun applyEditingMode() {
        val candidateId = getPassedCandidateId()!!
        val currentCandidate = candidateInteractor.getCandidateById(candidateId)!!
        applyCandidateInfoToView(currentCandidate)
    }

    private fun applyCandidateInfoToView(currentCandidate: CandidateListItem.Info) = view.setCandidateListItemInfo(currentCandidate)

    private fun getPassedCandidateId() = view.getPassedCandidateId()

    private enum class CurrentMode {
        EDITING, ADDING
    }
}
