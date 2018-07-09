package com.cookpad.candidateinfo.ui.activity.main

import com.cookpad.candidateinfo.ui.base.BasePresenter
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

interface MainPresenter: BasePresenter {
    fun onAddCandidateClicked()
    fun onMenuItemClicked(index: Int, item: CandidateListItem.Info)

}
