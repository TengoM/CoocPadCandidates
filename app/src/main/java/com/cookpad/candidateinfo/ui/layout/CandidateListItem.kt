package com.cookpad.candidateinfo.ui.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.cookpad.candidateinfo.R
import kotlinx.android.synthetic.main.candidate_item.view.*

class CandidateListItem: FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    init {
        View.inflate(context, R.layout.candidate_item, this)
    }

    fun fillInfo(item: Info) {
        uName.text = item.name
        uPhoneNumber.text = item.phoneNumber
        uEmail.text = item.email
        uAssessmentGrade.text = item.assessmentGrade
    }

    class Info(val id: Int, var name: String, var email: String, var phoneNumber: String, var assessmentGrade: String) {
        fun copyValues(candidate: Info) {
            this.name = candidate.name
            this.email = candidate.email
            this.phoneNumber = candidate.phoneNumber
            this.assessmentGrade = candidate.assessmentGrade
        }
    }
}