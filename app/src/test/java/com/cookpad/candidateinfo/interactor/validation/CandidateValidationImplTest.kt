package com.cookpad.candidateinfo.interactor.validation

import com.cookpad.candidateinfo.ui.layout.CandidateListItem
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CandidateValidationImplTest{

    private lateinit var candidateValidation: CandidateValidationImpl

    @Before
    fun setup(){
        candidateValidation = CandidateValidationImpl()
    }

    @Test
    fun testValidationForInvalidMailWithoutAt(){
        val candidate = CandidateListItem.Info(-1, "name", "mailGoogle", "222", "F")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertFalse(isValid)
        }
    }

    @Test
    fun testValidationForInvalidShortMail(){
        val candidate = CandidateListItem.Info(-1, "name", "m@il", "222", "F")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertFalse(isValid)
        }
    }

    @Test
    fun testValidationForInvalidName(){
        val candidate = CandidateListItem.Info(-1, "", "masda@il", "222", "F")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertFalse(isValid)
        }
    }

    @Test
    fun testValidationForInvalidNumber(){
        val candidate = CandidateListItem.Info(-1, "name", "m2121@il", "", "F")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertFalse(isValid)
        }
    }
    @Test
    fun testValidationForInvalidGrade(){
        val candidate = CandidateListItem.Info(-1, "name", "m1221@il", "222", "fail")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertFalse(isValid)
        }
    }
    @Test
    fun testValidationForValidInput(){
        val candidate = CandidateListItem.Info(-1, "name", "msasa@il", "222", "F")
        candidateValidation.checkIfCandidateInfoIsValid(candidate){
            isValid, displayTextResId ->
            assertTrue(isValid)
        }
    }


}