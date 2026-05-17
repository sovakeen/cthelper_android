package com.example.cthelper.domain.repository

import com.example.cthelper.domain.model.UserAnswer
import com.example.cthelper.domain.model.Test
import com.example.cthelper.remote.response.CompleteAttemptResponse
import com.example.cthelper.remote.response.ResumeAttemptResponse
import com.example.cthelper.remote.response.StartAttemptResponse

interface TestManagementRepository {
    suspend fun getTests(): List<Test>

    suspend fun startAttempt(testId: Int): StartAttemptResponse

    suspend fun pauseAttempt(attemptId: Int, userAnswers: List<UserAnswer>): Unit

    suspend fun resumeAttempt(attemptId: Int): ResumeAttemptResponse

    suspend fun completeAttempt(attemptId: Int, userAnswers: List<UserAnswer>): CompleteAttemptResponse

    suspend fun cancelAttempt(attemptId: Int): Unit
}
