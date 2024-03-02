package furhatos.app.projectskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language


class StuckAtStart : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                    "I don't know where to start?",
                    "How do I begin with an exercise like this?",
                    "I'm stuck",
                    "I am lost with this exercise"
        )
    }
}

class Incorrect : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I don't know where to start?",
                "How do I begin with an exercise like this?",
                "I'm stuck",
                "I am lost with this exercise"
            )
    }
}

class StuckReadWell : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "yes I did read the question",
            "Yes I understood the question",
            "I have read the assignment correctly, yes",
        )
    }
}

class ReadQuestionWell : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "When squares are put in a row it looks like this, 13 matches are needed for 4 squares. How many matches are needed to get 100 squares in a row?"
        )
    }
}

class Four : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "I see four",
            "I see 4",
            "I see four squares",
            "I see 4 squares"
        )
    }
}

class NotUnderstoodTask : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "I don't understand what the tasks asks of me",
            "I don't get what I'm supposed to do"
        )
    }
}

class NoIHaveNot : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "no I have not",
            "know",
            "no no"
        )
    }
}

class FurhatNeedHelp : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Hi Furhat, I need some help",
            "Hey Furhat, please help me",
            "I need Help",
            "Could you please help me?"
        )
    }
}