package com.cookpad.appdata.models

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class Candidate() : RealmObject(){

    companion object {
        @Ignore
        const val CANDIDATE_ID_NAME = "candidateId"

        @Ignore
        const val DEFAULT_ID = -1
    }

    constructor(candidateId: Int, email: String, phoneNumber: String, candidateName: String, assessmentGrade: String) : this() {
        this.candidateId = candidateId
        this.email = email
        this.phoneNumber = phoneNumber
        this.candidateName = candidateName
        this.assessmentGrade = assessmentGrade
    }

    constructor(copy: Candidate): this(
        copy.candidateId,
        copy.email,
        copy.phoneNumber,
        copy.candidateName,
        copy.assessmentGrade
    )

    @PrimaryKey
    var candidateId = DEFAULT_ID

    var email = ""

    var phoneNumber = ""
    var candidateName: String = ""
    var assessmentGrade = ""

}