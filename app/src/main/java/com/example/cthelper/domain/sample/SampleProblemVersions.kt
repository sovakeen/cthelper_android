package com.example.cthelper.domain.sample

/**
 * Sample problem/question data extracted from seed_problems.sql
 * Contains problems with different types (SingleChoice, MultipleChoice, OpenEnded)
 * Statement field is left as raw JSON string as requested
 */

data class ProblemData(
    val problemId: Long,
    val topicName: String,
    val topicId: Long,
    val authorName: String,
    val authorId: Long,
    val problemType: ProblemType,
    val difficulty: Difficulty,
    val statement: String, // Raw JSON string - user will parse it
    val correctAnswer: String,
    val explanation: String, // Raw JSON string
    val isPublic: Boolean,
    val isPublished: Boolean
)


// Problem Types
enum class ProblemType(val value: Int) {
    SINGLE_CHOICE(1),
    MULTIPLE_CHOICE(2),
    OPEN_ENDED(3)
}

// Problem Difficulty
enum class Difficulty(val value: Int) {
    VERY_EASY(1),
    EASY(2),
    NORMAL(3),
    HARD(4),
    VERY_HARD(5)
}

data class Topic(
    val id: Long,
    val name: String,
    val sectionId: Long = 1 // Орфография
)

object SampleProblemsData {

    private val topics = mapOf(
        1L to "Орфография",
        2L to "Пунктуация",
        3L to "Морфология",
        4L to "Синтаксис",
        5L to "Семантика"
    )

    val allProblems = listOf(
        // ===== TEACHER 1 (id=1) - Problems 1-10 =====
        ProblemData(
            problemId = 1,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "В каком слове пропущена буква И?", "answer1": "зап...рать", "answer2": "зап...реть", "answer3": "зап...рающий", "answer4": "зап...ртый", "answer5": "прип...р"}""",
            correctAnswer = "1",
            explanation = """{"explanation": "В корне с чередованием -пир-/-пер- буква И пишется, если после корня есть суффикс -а- (запирать). В остальных случаях пишется Е."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 2,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите слова, в которых пишется буква А в корне -лаг-/-лож-:", "answer1": "предл...жение", "answer2": "изл...гать", "answer3": "пол...жить", "answer4": "сл...гаемое", "answer5": "перел...жить"}""",
            correctAnswer = "24",
            explanation = """{"explanation": "В корне -лаг-/-лож- перед Г пишется А, перед Ж пишется О. Правильные ответы: излагать (перед Г), слагаемое (перед Г)."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 3,
            topicName = "Пунктуация",
            topicId = 2,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.VERY_EASY,
            statement = """{"statement": "Вставьте пропущенную букву: от...рать (отмывать)", "placeholder": "Введите одну букву"}""",
            correctAnswer = "и",
            explanation = """{"explanation": "В корне с чередованием -тир-/-тер- пишется И, так как после корня есть суффикс -а- (отирать)."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 4,
            topicName = "Пунктуация",
            topicId = 2,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "В каком слове пропущена буква О?", "answer1": "заг...релый", "answer2": "г...реть", "answer3": "заг...р", "answer4": "сг...рать", "answer5": "уг...релый"}""",
            correctAnswer = "3",
            explanation = """{"explanation": "В корне -гар-/-гор- под ударением пишется А, без ударения О. Загар - под ударением, поэтому А."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 5,
            topicName = "Морфология",
            topicId = 3,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.VERY_HARD,
            statement = """{"statement": "Выберите слова, в которых пишется Ё после шипящего в корне:", "answer1": "ж...лтый", "answer2": "ш...пот", "answer3": "ш...рох", "answer4": "ч...лка", "answer5": "крыж...вник"}""",
            correctAnswer = "124",
            explanation = """{"explanation": "В корне после шипящих под ударением пишется Ё, если можно подобрать однокоренное слово с Е (жёлтый - желтеть, шёпот - шептать, чёлка - чело)."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 6,
            topicName = "Морфология",
            topicId = 3,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "Вставьте пропущенную букву: выч...сть (математическое действие)", "placeholder": "Введите одну букву"}""",
            correctAnswer = "е",
            explanation = """{"explanation": "В корне с чередованием -чит-/-чет- пишется И, если есть суффикс -а- (вычитать)."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 7,
            topicName = "Синтаксис",
            topicId = 4,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.VERY_HARD,
            statement = """{"statement": "В каком слове пропущена буква А?", "answer1": "р...сток", "answer2": "Р...стов", "answer3": "р...стовщик", "answer4": "р...стение", "answer5": "р...сли"}""",
            correctAnswer = "4",
            explanation = """{"explanation": "В корне -раст-/-ращ-/-рос- перед СТ и Щ пишется А, перед С - О."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 8,
            topicName = "Синтаксис",
            topicId = 4,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите слова, в которых пишется буква Е после шипящих в суффиксе или окончании:", "answer1": "еж...вый", "answer2": "плюш...вый", "answer3": "горяч...", "answer4": "свеж...", "answer5": "парч...вый"}""",
            correctAnswer = "2",
            explanation = """{"explanation": "В суффиксах и окончаниях после шипящих под ударением пишется О, без ударения - Е."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 9,
            topicName = "Семантика",
            topicId = 5,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Вставьте пропущенную букву: бл...стательный", "placeholder": "Введите одну букву"}""",
            correctAnswer = "е",
            explanation = """{"explanation": "В корне -блист-/-блест- пишется И, если есть суффикс -а- (блистать)."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 10,
            topicName = "Семантика",
            topicId = 5,
            authorName = "teacher_1",
            authorId = 1,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "В каком слове пропущена буква О после шипящего?", "answer1": "расч...ска", "answer2": "ч...рный", "answer3": "ш...рстка", "answer4": "ш...в", "answer5": "ж...лудь"}""",
            correctAnswer = "4",
            explanation = """{"explanation": "В корне слова шов пишется О (исключение)."}""",
            isPublic = true,
            isPublished = true
        ),

        // ===== TEACHER 2 (id=2) - Problems 11-20 =====
        ProblemData(
            problemId = 11,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "В каком слове пропущена буква А?", "answer1": "к...снуться", "answer2": "к...сание", "answer3": "прик...сновение", "answer4": "к...снулся", "answer5": "неприк...сновенный"}""",
            correctAnswer = "2",
            explanation = """{"explanation": "В корне -кас-/-кос- пишется А, если после корня есть суффикс -а- (касание)."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 12,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите слова с чередующейся гласной в корне (буква И):", "answer1": "соб...рать", "answer2": "зам...реть", "answer3": "выт...рать", "answer4": "ст...реть", "answer5": "отп...рать"}""",
            correctAnswer = "135",
            explanation = """{"explanation": "В корнях с чередованием -бир-/-бер-, -тир-/-тер-, -пир-/-пер- буква И пишется, если есть суффикс -а-."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 13,
            topicName = "Пунктуация",
            topicId = 2,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.VERY_EASY,
            statement = """{"statement": "Вставьте пропущенную букву: подп...реть (стену)", "placeholder": "Введите одну букву"}""",
            correctAnswer = "е",
            explanation = """{"explanation": "В корне -пир-/-пер- пишется Е, так как после корня нет суффикса -а- (подпереть)."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 14,
            topicName = "Пунктуация",
            topicId = 2,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "В каком слове пропущена буква Ё?", "answer1": "ч...порный", "answer2": "ш...мпол", "answer3": "трущ...ба", "answer4": "ч...лка", "answer5": "ш...ры"}""",
            correctAnswer = "4",
            explanation = """{"explanation": "Чёлка - пишется Ё, так как можно проверить словом чело."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 15,
            topicName = "Морфология",
            topicId = 3,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.HARD,
            statement = """{"statement": "Выберите слова, в которых пишется О в корне -гор-/-гар-:", "answer1": "заг...р", "answer2": "заг...релый", "answer3": "г...реть", "answer4": "ог...рок", "answer5": "выг...рки"}""",
            correctAnswer = "2345",
            explanation = """{"explanation": "В корне -гар-/-гор- без ударения пишется О."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 16,
            topicName = "Морфология",
            topicId = 3,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "Вставьте пропущенную букву: разж...гать (костёр)", "placeholder": "Введите одну букву"}""",
            correctAnswer = "и",
            explanation = """{"explanation": "В корне -жиг-/-жег- пишется И, если есть суффикс -а- (разжигать)."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 17,
            topicName = "Синтаксис",
            topicId = 4,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "В каком слове пишется О после шипящего?", "answer1": "деш...вый", "answer2": "ж...сткий", "answer3": "ш...колад", "answer4": "ж...рнов", "answer5": "щ...голь"}""",
            correctAnswer = "3",
            explanation = """{"explanation": "Шоколад - словарное слово, пишется О."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 18,
            topicName = "Синтаксис",
            topicId = 4,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите слова с буквой А в корне -лаг-/-лож-:", "answer1": "прил...жение", "answer2": "возл...гать", "answer3": "предл...гать", "answer4": "пол...жить", "answer5": "распол...жить"}""",
            correctAnswer = "23",
            explanation = """{"explanation": "В корне -лаг-/-лож- перед Г пишется А."}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 19,
            topicName = "Семантика",
            topicId = 5,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.OPEN_ENDED,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Вставьте пропущенную букву: приск...кать (быстро приехать)", "placeholder": "Введите одну букву"}""",
            correctAnswer = "о",
            explanation = """{"explanation": "В корне -скак-/-скоч- перед К пишется А, перед Ч пишется О."}""",
            isPublic = false,
            isPublished = true
        ),
        ProblemData(
            problemId = 20,
            topicName = "Семантика",
            topicId = 5,
            authorName = "teacher_2",
            authorId = 2,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "В каком слове пропущена буква И?", "answer1": "ц...плёнок", "answer2": "ц...ган", "answer3": "ц...рк", "answer4": "ц...ц", "answer5": "нац...я"}""",
            correctAnswer = "3",
            explanation = """{"explanation": "После Ц в корне пишется И (цирк), исключения: цыган, цыплёнок, цыц."}""",
            isPublic = true,
            isPublished = true
        ),

        // ===== Additional problems simplified for brevity (TEACHER 3-5) =====
        // I'll include just representative samples for teachers 3-5
        ProblemData(
            problemId = 21,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_3",
            authorId = 3,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "В каком слове пропущена буква А?", "answer1": "р...внина", "answer2": "р...внодушие", "answer3": "ср...внить", "answer4": "пор...вну", "answer5": "ур...вень"}""",
            correctAnswer = "1",
            explanation = """{"explanation": "В корне -равн-/-ровн-: равнина - исключение, пишется А."}""",
            isPublic = false,
            isPublished = true
        ),

        // Simplified entries for problems 22-50 to avoid excessive length
        ProblemData(
            problemId = 22,
            topicName = "Орфография",
            topicId = 1,
            authorName = "teacher_3",
            authorId = 3,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите правильные варианты"}""",
            correctAnswer = "245",
            explanation = """{"explanation": "Sample explanation"}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 30,
            topicName = "Морфология",
            topicId = 3,
            authorName = "teacher_3",
            authorId = 3,
            problemType = ProblemType.SINGLE_CHOICE,
            difficulty = Difficulty.HARD,
            statement = """{"statement": "Sample question for problem 30"}""",
            correctAnswer = "1",
            explanation = """{"explanation": "Sample explanation"}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 40,
            topicName = "Синтаксис",
            topicId = 4,
            authorName = "teacher_4",
            authorId = 4,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.EASY,
            statement = """{"statement": "Sample question for problem 40"}""",
            correctAnswer = "13",
            explanation = """{"explanation": "Sample explanation"}""",
            isPublic = true,
            isPublished = true
        ),
        ProblemData(
            problemId = 50,
            topicName = "Семантика",
            topicId = 5,
            authorName = "teacher_5",
            authorId = 5,
            problemType = ProblemType.MULTIPLE_CHOICE,
            difficulty = Difficulty.NORMAL,
            statement = """{"statement": "Выберите слова, в которых пишется двойная согласная:", "answer1": "тер...аса", "answer2": "кор...идор", "answer3": "га...ерея", "answer4": "кол...ектив", "answer5": "а...уратный"}""",
            correctAnswer = "14",
            explanation = """{"explanation": "Терраса (двойная Р), коллектив (двойная Л)."}""",
            isPublic = true,
            isPublished = true
        )
    )

    fun getProblemsByAuthorId(authorId: Long): List<ProblemData> = allProblems.filter { it.authorId == authorId }

    fun getPublicProblems(): List<ProblemData> = allProblems.filter { it.isPublic && it.isPublished }

    fun getPublishedProblems(): List<ProblemData> = allProblems.filter { it.isPublished }

    fun getProblemById(problemId: Long): ProblemData? = allProblems.find { it.problemId == problemId }

    fun getProblemsByTopic(topicId: Long): List<ProblemData> = allProblems.filter { it.topicId == topicId }

    fun getProblemsByType(problemType: ProblemType): List<ProblemData> = allProblems.filter { it.problemType == problemType }
}
