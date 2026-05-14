package com.example.cthelper.data

import com.example.cthelper.data.local.models.ProblemVersion
import com.example.cthelper.data.local.models.Test

interface CTHelperApi {
    fun getTests(): List<Test>

    fun getTestProblems(testId: Int): List<ProblemVersion>
}

class CTHelperApiImpl : CTHelperApi {
    override fun getTests(): List<Test> {
        return 0
    }

    override fun getTestProblems(): List<ProblemVersion> {
        return 0
    }
}