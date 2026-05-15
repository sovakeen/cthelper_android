package com.example.cthelper.data.remote.response

import com.example.cthelper.data.local.models.Difficulty
import com.example.cthelper.data.local.models.ProblemType
import com.example.cthelper.data.local.models.ProblemVersion

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
