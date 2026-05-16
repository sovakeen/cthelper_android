package com.example.cthelper.remote.response

import com.example.cthelper.domain.model.Difficulty
import com.example.cthelper.domain.model.ProblemType
import com.example.cthelper.domain.model.ProblemVersion

data class ProblemVersionResponse(
    val problemId: Int,
    val code: String,
    val type: Int,
    val difficulty: Int,
    val statement: String,
    val answer: String,
    val explanation: String
) {
    fun toProblemVersion(): ProblemVersion = ProblemVersion(
        problemId = problemId,
        code = code,
        type = ProblemType.fromInt(type),
        difficulty = Difficulty.fromInt(type),
        statement = statement,
        answer = answer,
        explanation = explanation
    )
}
