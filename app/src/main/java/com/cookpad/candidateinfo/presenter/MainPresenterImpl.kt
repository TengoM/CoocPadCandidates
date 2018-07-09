package com.cookpad.candidateinfo.presenter

import com.cookpad.candidateinfo.MyApp
import com.cookpad.candidateinfo.interactor.candidate.CandidateInteractor
import com.cookpad.candidateinfo.interactor.candidate.CandidateUpdateCallback
import com.cookpad.candidateinfo.ui.activity.main.MainPresenter
import com.cookpad.candidateinfo.ui.activity.main.MainView
import com.cookpad.candidateinfo.ui.helper.SwipeMenuListHelper
import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import javax.inject.Inject

class MainPresenterImpl(private val view: MainView, myApp: MyApp): MainPresenter, CandidateUpdateCallback {

    init {
        myApp.appComponent.inject(this)
    }

    @Inject
    lateinit var candidateInteractor: CandidateInteractor

    override fun onRenderView() {
        val allCandidates = candidateInteractor.getAllCandidates()
        candidateInteractor.registerCandidateUpdateListener(this)
        if(allCandidates.isEmpty()){
            showEmptyContent()
        }else{
            hideEmptyText()
            pushCandidatesIntoView(allCandidates)
        }
    }

    private fun hideEmptyText() = view.hideEmptyContent()
    private fun showEmptyContent() = view.showEmptyContent()

    private fun pushCandidatesIntoView(candidates: List<CandidateListItem.Info>) {
        view.setupListView()
        view.addItemsIntoList(candidates)
    }

    override fun onAddCandidateClicked() {
        view.pushCandidateDetailActivity(null)
    }

    override fun onMenuItemClicked(index: Int, item: CandidateListItem.Info) {
        val isActionDelete = SwipeMenuListHelper.isActionDelete(index)
        if(isActionDelete){
            candidateInteractor.deleteCandidateWithId(item.id)
            onCandidateDeleted(item)
        }else{
            view.pushCandidateDetailActivity(item.id)
        }
    }

    private fun onCandidateDeleted(candidate: CandidateListItem.Info?) {
        if(candidate != null){
            view.deleteCandidate(candidate)
        }
        val candidateCount = candidateInteractor.getCandidateCount()
        if(candidateCount < 1)
            view.showEmptyContent()
    }

    override fun onCandidateUpdated(candidate: CandidateListItem.Info) = view.updateOrAddCandidate(candidate)

    override fun onCandidateAdded(candidate: CandidateListItem.Info){
        view.updateOrAddCandidate(candidate)
        view.hideEmptyContent()
    }

}