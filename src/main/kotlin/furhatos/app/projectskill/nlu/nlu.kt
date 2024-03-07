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

class Three : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "You need 3 more matches for the next square",
            "I think you need 3",
            "I would say 3",
            "The answer is 3",
            "You need 3 matches"
        )
    }
}

class FurhatNeedHelp : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Hi Furhat, I need some help",
            "Hey Furhat, please help me",
            "I need help",
            "Could you please help me?",
            "Can you help me?",
            "Help",
            "Please help"
        )
    }
}

class FurhatCheckSolution : Intent() {
    override fun getExamples(lang : Language): List<String>{
        return listOf(
            "Hey Furhat, could you check my solution?",
            "I think I've found a solution, could you check it for me",
            "Could you check my answer",
            "Hi furhat, I have an answer, can you check it for me?",
            "I've found the correct answer!",
            "Can you check my solution?"
        )
    }
}

class CorrectSolution : Intent() {
    override fun getExamples(lang: Language): List<String>{
        return listOf(
            "301",
            "three hundred and one",
            "3x + 1",
            "The amount of matches for 100 squares is 301",
            "You need 3001 matches",
            "The correct formula is 3x + 1",
            "I think you need 3001 matches",
            "You need 301 matches",
            "I think the answer is 301",
            "I think the answer is 3001",
            "I think you need about 301"
        )
    }
}

