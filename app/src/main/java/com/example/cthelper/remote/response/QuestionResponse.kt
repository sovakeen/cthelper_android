package com.example.cthelper.remote.response

import com.example.cthelper.domain.model.Difficulty
import com.example.cthelper.domain.model.QuestionType
import com.example.cthelper.domain.model.Question

data class QuestionResponse(
    val questionId: Int,
    val code: String,
    val type: Int,
    val difficulty: Int,
    val statement: String,
    val answer: String,
    val explanation: String
) {
    fun toQuestion(): Question = Question(
        questionId = questionId,
        code = code,
        type = QuestionType.fromInt(type),
        difficulty = Difficulty.fromInt(type),
        statement = statement,
        answer = answer,
        explanation = explanation
    )
}
