package com.cookpad.candidateinfo.ui.activity.main

import android.view.View
import com.cookpad.candidateinfo.MyApp
import com.cookpad.candidateinfo.R
import com.cookpad.candidateinfo.presenter.MainPresenterImpl
import com.cookpad.candidateinfo.ui.activity.candidate.CandidateDetailActivity
import com.cookpad.candidateinfo.ui.adapter.CandidateAdapter
import com.cookpad.candidateinfo.ui.base.BaseActivity
import com.cookpad.candidateinfo.ui.helper.SwipeMenuListHelper
import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.empty_layout.*

class MainActivity : BaseActivity(), MainView {


    private lateinit var presenter: MainPresenter
    private lateinit var adapter: CandidateAdapter

    override val layoutId = R.layout.activity_main
    override fun renderView() {
        setSupportActionBar(toolbar)
        setupListView()
    }

    override fun onResume() {
        super.onResume()
        initAndStartPresenter()
        setupClickListeners()
    }

    private fun setupClickListeners(){
        uFab.setOnClickListener{
            presenter.onAddCandidateClicked()
        }
    }

    private fun initAndStartPresenter(){
        presenter = MainPresenterImpl(this, application as MyApp)
        presenter.onRenderView()
    }


    override fun setupListView(){
        adapter = CandidateAdapter(layoutInflater)
        uListView.adapter = adapter
        uListView.setMenuCreator(SwipeMenuListHelper.getMenuCreator(this))
        uListView.setOnMenuItemClickListener{
            position, _, index ->
            val item = adapter.getItem(position)
            presenter.onMenuItemClicked(index, item)
            true
        }
    }

    override fun showEmptyContent(){ uEmptyText.visibility = View.VISIBLE }

    override fun hideEmptyContent() { uEmptyText.visibility = View.GONE }

    override fun addItemsIntoList(candidates: List<CandidateListItem.Info>) = adapter.replaceItems(candidates)

    override fun pushCandidateDetailActivity(candidateId: Int?) {
        CandidateDetailActivity.start(candidateId, this)
    }

    override fun updateOrAddCandidate(candidate: CandidateListItem.Info) = adapter.updateOrAddItem(candidate)

    override fun deleteCandidate(candidate: CandidateListItem.Info) = adapter.deleteItems(candidate)
}
