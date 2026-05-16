package com.example.cthelper.domain.sample

/**
 * Sample test data extracted from seed_tests.sql
 * Contains test information with author names and subject details
 */

data class TestData(
    val testId: Long,
    val testName: String,
    val authorName: String,
    val authorId: Long,
    val problemCount: Int,
    val avgDifficulty: String, // VeryEasy, Easy, Normal, Hard, VeryHard
    val type: Int, // 1=State, 2=Custom, 3=Mixed
    val isPublished: Boolean,
    val isPublic: Boolean,
    val duration: Int,
    val attemptsCount: Int,
    val subjectId: Long,
    val subjectName: String,
    val problems: List<TestProblemMapping>
)

data class TestProblemMapping(
    val problemId: Long,
    val code: String
)

object SampleTestsData {
    
    val russianLanguageSubject = "Русский язык"
    
    val allTests = listOf(
        // TEACHER 1 (id=1, name=teacher_1)
        TestData(
            testId = 1,
            testName = "Русский язык - Орфография (Публичный тест)",
            authorName = "teacher_1",
            authorId = 1,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = true,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(2, "A1"),
                TestProblemMapping(4, "A2"),
                TestProblemMapping(6, "B1"),
                TestProblemMapping(8, "A3"),
                TestProblemMapping(12, "A4"),
                TestProblemMapping(22, "A5")
            )
        ),
        TestData(
            testId = 2,
            testName = "Русский язык - Орфография (Приватный тест)",
            authorName = "teacher_1",
            authorId = 1,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(1, "A1"),
                TestProblemMapping(3, "B1"),
                TestProblemMapping(5, "A2"),
                TestProblemMapping(7, "A3"),
                TestProblemMapping(32, "A4"),
                TestProblemMapping(42, "A5")
            )
        ),
        TestData(
            testId = 3,
            testName = "Русский язык - Орфография (Черновик)",
            authorName = "teacher_1",
            authorId = 1,
            problemCount = 2,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = false,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(9, "B1"),
                TestProblemMapping(10, "B2")
            )
        ),

        // TEACHER 2 (id=2, name=teacher_2)
        TestData(
            testId = 4,
            testName = "Русский язык - Орфография (Публичный тест)",
            authorName = "teacher_2",
            authorId = 2,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = true,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(12, "A1"),
                TestProblemMapping(14, "A2"),
                TestProblemMapping(16, "B1"),
                TestProblemMapping(18, "A3"),
                TestProblemMapping(32, "A4"),
                TestProblemMapping(42, "A5")
            )
        ),
        TestData(
            testId = 5,
            testName = "Русский язык - Орфография (Приватный тест)",
            authorName = "teacher_2",
            authorId = 2,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(11, "A1"),
                TestProblemMapping(13, "B1"),
                TestProblemMapping(15, "A2"),
                TestProblemMapping(17, "A3"),
                TestProblemMapping(2, "A4"),
                TestProblemMapping(22, "A5")
            )
        ),
        TestData(
            testId = 6,
            testName = "Русский язык - Орфография (Черновик)",
            authorName = "teacher_2",
            authorId = 2,
            problemCount = 2,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = false,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(19, "B1"),
                TestProblemMapping(20, "B2")
            )
        ),

        // TEACHER 3 (id=3, name=teacher_3)
        TestData(
            testId = 7,
            testName = "Русский язык - Орфография (Публичный тест)",
            authorName = "teacher_3",
            authorId = 3,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = true,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(22, "A1"),
                TestProblemMapping(24, "A2"),
                TestProblemMapping(26, "B1"),
                TestProblemMapping(28, "A3"),
                TestProblemMapping(2, "A4"),
                TestProblemMapping(12, "A5")
            )
        ),
        TestData(
            testId = 8,
            testName = "Русский язык - Орфография (Приватный тест)",
            authorName = "teacher_3",
            authorId = 3,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(21, "A1"),
                TestProblemMapping(23, "B1"),
                TestProblemMapping(25, "A2"),
                TestProblemMapping(27, "A3"),
                TestProblemMapping(32, "A4"),
                TestProblemMapping(42, "A5")
            )
        ),
        TestData(
            testId = 9,
            testName = "Русский язык - Орфография (Черновик)",
            authorName = "teacher_3",
            authorId = 3,
            problemCount = 2,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = false,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(29, "B1"),
                TestProblemMapping(30, "B2")
            )
        ),

        // TEACHER 4 (id=4, name=teacher_4)
        TestData(
            testId = 10,
            testName = "Русский язык - Орфография (Публичный тест)",
            authorName = "teacher_4",
            authorId = 4,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = true,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(32, "A1"),
                TestProblemMapping(34, "A2"),
                TestProblemMapping(36, "B1"),
                TestProblemMapping(38, "A3"),
                TestProblemMapping(2, "A4"),
                TestProblemMapping(12, "A5")
            )
        ),
        TestData(
            testId = 11,
            testName = "Русский язык - Орфография (Приватный тест)",
            authorName = "teacher_4",
            authorId = 4,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(31, "A1"),
                TestProblemMapping(33, "B1"),
                TestProblemMapping(35, "A2"),
                TestProblemMapping(37, "A3"),
                TestProblemMapping(22, "A4"),
                TestProblemMapping(42, "A5")
            )
        ),
        TestData(
            testId = 12,
            testName = "Русский язык - Орфография (Черновик)",
            authorName = "teacher_4",
            authorId = 4,
            problemCount = 2,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = false,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(39, "B1"),
                TestProblemMapping(40, "B2")
            )
        ),

        // TEACHER 5 (id=5, name=teacher_5)
        TestData(
            testId = 13,
            testName = "Русский язык - Орфография (Публичный тест)",
            authorName = "teacher_5",
            authorId = 5,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = true,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(42, "A1"),
                TestProblemMapping(44, "A2"),
                TestProblemMapping(46, "B1"),
                TestProblemMapping(48, "A3"),
                TestProblemMapping(2, "A4"),
                TestProblemMapping(12, "A5")
            )
        ),
        TestData(
            testId = 14,
            testName = "Русский язык - Орфография (Приватный тест)",
            authorName = "teacher_5",
            authorId = 5,
            problemCount = 6,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = true,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(41, "A1"),
                TestProblemMapping(43, "B1"),
                TestProblemMapping(45, "A2"),
                TestProblemMapping(47, "A3"),
                TestProblemMapping(22, "A4"),
                TestProblemMapping(32, "A5")
            )
        ),
        TestData(
            testId = 15,
            testName = "Русский язык - Орфография (Черновик)",
            authorName = "teacher_5",
            authorId = 5,
            problemCount = 2,
            avgDifficulty = "Normal",
            type = 2,
            isPublished = false,
            isPublic = false,
            duration = 60,
            attemptsCount = 3,
            subjectId = 1,
            subjectName = russianLanguageSubject,
            problems = listOf(
                TestProblemMapping(49, "B1"),
                TestProblemMapping(50, "B2")
            )
        )
    )

    fun getTestsByAuthorId(authorId: Long): List<TestData> = allTests.filter { it.authorId == authorId }
    
    fun getPublicTests(): List<TestData> = allTests.filter { it.isPublic && it.isPublished }
    
    fun getPublishedTests(): List<TestData> = allTests.filter { it.isPublished }
    
    fun getTestById(testId: Long): TestData? = allTests.find { it.testId == testId }
}
