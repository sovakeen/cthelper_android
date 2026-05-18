package com.example.cthelper.util

import com.example.cthelper.model.enums.QuestionType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

sealed class QuestionStatement {
    data class Choice(
        val statement: String,
        val options: List<Option>
    ) : QuestionStatement() {
        data class Option(val key: String, val value: String)
    }

    data class OpenEnded(
        val statement: String,
        val placeholder: String?
    ) : QuestionStatement()

    companion object {
        fun parse(type: QuestionType, jsonString: String): QuestionStatement {
            return try {
                val json = Json.Default.parseToJsonElement(jsonString).jsonObject
                val statement = json["statement"]?.jsonPrimitive?.content ?: ""

                when (type) {
                    QuestionType.SINGLE_CHOICE, QuestionType.MULTIPLE_CHOICE -> {
                        val options = json.entries
                            .filter { it.key.startsWith("answer") }
                            .sortedBy { it.key.removePrefix("answer").toIntOrNull() ?: 0 }
                            .map { Choice.Option(it.key, it.value.jsonPrimitive.content) }
                        Choice(statement, options)
                    }
                    QuestionType.OPEN_ENDED -> {
                        val placeholder = json["placeholder"]?.jsonPrimitive?.content
                        OpenEnded(statement, placeholder)
                    }
                }
            } catch (e: Exception) {
                // Fallback in case of malformed JSON
                when (type) {
                    QuestionType.OPEN_ENDED -> OpenEnded(jsonString, null)
                    else -> Choice(jsonString, emptyList())
                }
            }
        }
    }
}