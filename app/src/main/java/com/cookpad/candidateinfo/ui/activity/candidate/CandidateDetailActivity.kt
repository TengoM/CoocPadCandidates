package com.cookpad.candidateinfo.ui.activity.candidate

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.cookpad.candidateinfo.MyApp
import com.cookpad.candidateinfo.R
import com.cookpad.candidateinfo.presenter.CandidateDetailPresenterImpl
import com.cookpad.candidateinfo.ui.base.BaseActivity
import com.cookpad.candidateinfo.ui.dialogs.GradePickDialog
import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import kotlinx.android.synthetic.main.activity_candidate_detail.*

class CandidateDetailActivity : BaseActivity(), CandidateDetailView {

    companion object {
        private const val CANDIDATE_ID_EXTRA = "candidate_id"
        fun start(candidateId: Int?, context: Context) {
            val intent = Intent(context, CandidateDetailActivity::class.java)
            if (candidateId != null)
                intent.putExtra(CANDIDATE_ID_EXTRA, candidateId)
            context.startActivity(intent)
        }
    }

    private lateinit var presenter: CandidateDetailPresenter

    override val layoutId = R.layout.activity_candidate_detail
    override fun renderView() {

    }

    override fun onResume() {
        super.onResume()
        initAndStartPresenter()
    }

    private fun initAndStartPresenter() {
        presenter = CandidateDetailPresenterImpl(this, application as MyApp)
        presenter.onRenderView()
    }

    override fun setupClickListeners() {
        uSubmit.setOnClickListener{
            presenter.onSubmitClicked()
        }
        uAssessmentGrade.setOnClickListener{
            presenter.onGradeClicked()
        }
    }

    override fun showToastFor(textResId: Int) = Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()

    override fun getNameText(): String = uName.text.toString()

    override fun getEmailText(): String = uMail.text.toString()

    override fun getPhoneText(): String = uPhoneNumber.text.toString()

    override fun getGradeText(): String = uAssessmentGrade.text.toString()

    override fun finishActivity() = finish()

    override fun pushGradeChooseDialogForGrades(gradeList: List<String>) {
        GradePickDialog.getDialog(this, gradeList){
            grade ->
            uAssessmentGrade.text = grade
        }
    }

    override fun updateSubmitButtonText(submitButtonName: Int) = uSubmit.setText(submitButtonName)

    override fun setCandidateListItemInfo(currentCandidate: CandidateListItem.Info) {
        uName.setText(currentCandidate.name)
        uMail.setText(currentCandidate.email)
        uPhoneNumber.setText(currentCandidate.phoneNumber)
        uAssessmentGrade.text = currentCandidate.assessmentGrade
    }

    override fun getPassedCandidateId(): Int? =
            if (intent.hasExtra(CANDIDATE_ID_EXTRA))
                intent.getIntExtra(CANDIDATE_ID_EXTRA, -1)
            else null
}
