package com.cookpad.appdata

import com.cookpad.appdata.models.Candidate
import io.realm.Realm

class RealmInteractor : CandidateRepository {

    private val realmInstance = Realm.getDefaultInstance()

    override fun getAllCandidates(): List<Candidate> = realmInstance.where(Candidate::class.java).findAll()
            ?: emptyList()

    override fun getCandidateById(id: Int): Candidate?
        = realmInstance.where(Candidate::class.java).equalTo(Candidate.CANDIDATE_ID_NAME, id).findFirst()

    override fun updateCandidate(candidate: Candidate) = insertOrUpdateCandidate(candidate)

    override fun addCandidate(candidate: Candidate) {
        val newId = getHighestIdInCandidates() + 1
        candidate.candidateId = newId
        insertOrUpdateCandidate(candidate)
    }

    override fun deleteCandidateWithId(id: Int) {
        realmInstance.executeTransaction {
            getCandidateById(id)?.deleteFromRealm()
        }
    }


    override fun getCandidateCount() = realmInstance.where(Candidate::class.java).count().toInt()

    override fun addListChangeCallback(changeCallback: CandidateChangeCallback) {
        val items = realmInstance.where(Candidate::class.java).findAll()
        items.addChangeListener{
            list, changeSet ->
            val addedElements = getElementsFromIndex(list, changeSet.insertions.toList())
            val changedElements = getElementsFromIndex(list, changeSet.changes.toList())
            changeCallback.itemsAdded(addedElements)
            changeCallback.itemsChanged(changedElements)
        }
    }

    private fun getElementsFromIndex(list: List<Candidate>, indexList: List<Int>): List<Candidate>{
        val retList = mutableListOf<Candidate>()
        indexList.forEach{
            val element = list.getOrNull(it)
            if(element != null){
                retList.add(element)
            }
        }
        return retList.toList()
    }

    private fun insertOrUpdateCandidate(candidate: Candidate){
        realmInstance.executeTransaction {
            realm ->
            realm.insertOrUpdate(candidate)
        }
    }

    private fun getHighestIdInCandidates() = getAllCandidates().maxBy { it.candidateId }?.candidateId ?: 0

}