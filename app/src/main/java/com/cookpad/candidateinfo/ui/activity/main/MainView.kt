package com.cookpad.candidateinfo.ui.activity.main

import com.cookpad.candidateinfo.ui.base.BaseView
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface MainView: BaseView {
    fun setupListView()
    fun showEmptyContent()
    fun hideEmptyContent()
    fun addItemsIntoList(candidates: List<CandidateListItem.Info>)
    fun pushCandidateDetailActivity(candidateId: Int?)
    fun updateOrAddCandidate(candidate: CandidateListItem.Info)
    fun deleteCandidate(candidate: CandidateListItem.Info)
}