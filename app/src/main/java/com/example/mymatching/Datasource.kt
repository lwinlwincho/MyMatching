package com.example.mymatching

class Datasource {
    fun loadQuestion(): List<Question> {
        return listOf<Question>(
            Question(R.string.question1),
            Question(R.string.question2),
            Question(R.string.question3),
            Question(R.string.question4),
            Question(R.string.question5)
        )
    }

    fun loadAnswer(): List<Question> {
        return listOf<Question>(
            Question(R.string.answer1),
            Question(R.string.answer2),
            Question(R.string.answer3),
            Question(R.string.answer4),
            Question(R.string.answer5)
        )
    }


}