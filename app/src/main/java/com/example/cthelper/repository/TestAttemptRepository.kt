package com.example.cthelper.repository

import com.example.cthelper.model.UserAnswer
import com.example.cthelper.dto.testattempt.CompleteAttemptResponse
import com.example.cthelper.dto.testattempt.ResumeAttemptResponse
import com.example.cthelper.dto.testattempt.StartAttemptResponse

interface TestAttemptRepository {
    suspend fun startAttempt(testId: Int): StartAttemptResponse

    suspend fun pauseAttempt(attemptId: Int, userAnswers: List<UserAnswer>): Unit

    suspend fun resumeAttempt(attemptId: Int): ResumeAttemptResponse

    suspend fun completeAttempt(attemptId: Int, userAnswers: List<UserAnswer>): CompleteAttemptResponse

    suspend fun cancelAttempt(attemptId: Int): Unit
}
