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
            "I have read the assignment correctly, yes"
        )
    }
}
