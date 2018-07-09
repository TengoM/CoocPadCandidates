package com.cookpad.appdata

import com.cookpad.appdata.models.Candidate

interface CandidateChangeCallback {
    fun itemsChanged(list: List<Candidate>)
    fun itemsAdded(list: List<Candidate>)
}