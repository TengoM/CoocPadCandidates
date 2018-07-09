package com.cookpad.candidateinfo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import com.cookpad.candidateinfo.R
import com.cookpad.candidateinfo.ui.base.adapter.BaseListAdapter
import com.cookpad.candidateinfo.ui.base.adapter.BaseViewHolder
import com.cookpad.candidateinfo.ui.layout.CandidateListItem

class CandidateAdapter(layoutInflater: LayoutInflater) : BaseListAdapter<CandidateListItem.Info>(layoutInflater) {


    fun updateOrAddItem(candidate: CandidateListItem.Info) {
        val locItem = mList.find { it.id == candidate.id }
        if (locItem == null) {
            addItems(listOf(candidate))
        } else {
            locItem.copyValues(candidate)
        }
        notifyDataSetChanged()
    }

    fun deleteItems(candidate: CandidateListItem.Info) {
        mList.remove(mList.find { it.id == candidate.id })
        notifyDataSetChanged()
    }

    override fun layoutIdForType(type: Int) = R.layout.candidate_list_item
    override fun getNewHolderForType(type: Int) = ViewHolder()

    class ViewHolder : BaseViewHolder<CandidateListItem.Info> {
        private lateinit var uCandidateListItem: CandidateListItem
        override fun renderView(v: View) {
            uCandidateListItem = v as CandidateListItem
        }

        override fun fillView(item: CandidateListItem.Info, position: Int) {
            uCandidateListItem.fillInfo(item)
        }
    }
}