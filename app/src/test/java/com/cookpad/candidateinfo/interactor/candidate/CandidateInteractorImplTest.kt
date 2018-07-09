package com.cookpad.candidateinfo.interactor.candidate

import com.cookpad.appdata.CandidateRepository
import com.cookpad.appdata.models.Candidate
import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CandidateInteractorImplTest{

    @Mock
    lateinit var repository: CandidateRepository

    lateinit var candidateInteractorImpl: CandidateInteractorImpl

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        candidateInteractorImpl = CandidateInteractorImpl(repository)
    }


    @Test
    fun getAllCandidatesConvertingTest(){
        `when`(repository.getAllCandidates()).thenReturn(listOf(getDefaultCandidate()))
        val items = candidateInteractorImpl.getAllCandidates()
        assertEquals(getDefaultCandidate().candidateId, items.size)
    }

    @Test
    fun getAllCandidatesConvertingTestForNull(){
        `when`(repository.getAllCandidates()).thenReturn(listOf(getDefaultCandidate()))
        val items = candidateInteractorImpl.getAllCandidates()
        assertEquals(1, items.size)
    }


    @Test
    fun getCandidateByIdTest(){
        `when`(repository.getCandidateById(1)).thenReturn(getDefaultCandidate())
        val items = candidateInteractorImpl.getCandidateById(1)
        assertEquals(getDefaultCandidate().email, items?.email)
    }

    @Test
    fun testConvertDbIntoViewModel(){
        val item = candidateInteractorImpl.convertDbIntoViewModel(null)
        assertEquals(null, item)
    }

    @Test
    fun testConvertViewModelIntoDb(){
        val item = candidateInteractorImpl.convertViewIntoDbModel(getDefaultCandidateListInfo())
        assertEquals(getDefaultCandidateListInfo().id, item.candidateId)
    }

    @Test
    fun testGetCandidateCount(){
        `when`(repository.getCandidateCount()).thenReturn(1)
        val count = candidateInteractorImpl.getCandidateCount()
        assertEquals(1, count)
    }


    private fun getDefaultCandidate(): Candidate = Candidate(1, "name@gmail.com", "222", "name", "F")
    private fun getDefaultCandidateListInfo() = CandidateListItem.Info(1, "name", "name@gmail.com", "222", "A")
}