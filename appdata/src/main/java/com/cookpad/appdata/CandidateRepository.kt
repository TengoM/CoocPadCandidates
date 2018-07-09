package com.cookpad.appdata

import com.cookpad.appdata.models.Candidate

interface CandidateRepository {
    fun getAllCandidates(): List<Candidate>
    fun getCandidateById(id: Int): Candidate?
    fun updateCandidate(candidate: Candidate)
    fun addCandidate(candidate: Candidate)
    fun deleteCandidateWithId(id: Int)
    fun getCandidateCount(): Int
    fun addListChangeCallback(changeCallback: CandidateChangeCallback)

}