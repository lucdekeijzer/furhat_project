package furhatos.app.projectskill.flow.main
import furhatos.app.projectskill.setting.*

import furhatos.app.projectskill.flow.Parent
import furhatos.app.projectskill.*
import furhatos.app.projectskill.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.Intent.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.gestures.Gestures

//fun check_for_word(input: String){
//    var userInput: String? = null
//
//                // Keep prompting the user until the word "hello" is not in the response
//                while (userInput == null || $input in userInput) {
//                    furhat.say("Could you tell me how many squares are in the figure?")
//                    userInput = furhat.listen(timeout = 5000).toString()
//                    userInput.lowercase()
//
//                    if ($input in userInput) {
//                        furhat.say("Very good!")
//                        goto(Greeting)
//                    } else {
//                        furhat.say("Please have another look" + Gestures.Smile)
//                    }
//
//                }
//}



val StudentMisInterpretation: State = state(Parent) {
    // Main stuck state. This state is the goto state, the other states are call() states which terminate after going through them.
    // In this state, you could go through the whole stuck file if need be.
    onEntry {
        furhat.attend(users.random)
        var read_exercise_YN = furhat.askYN("Let's see if you have understood the exercise correctly. First of all, have you read the exercise?")
        if (read_exercise_YN){
            read_exercise = true
            var understood = furhat.askYN("Are you sure you understood the question and the figures given to you?")
            if (understood) {
                call(UnderstandingFigure)
                furhat.say("Good to hear! If you're still stuck, maybe try solving an easier exercise like 30 matches? And remember that if you have any other questions, just ask for help and I will help you explore the figure and what to do next!")
                goto(Idle)
            } else{
                call(UnderstandingFigure)
                call(UnderstandingNextStep)
                furhat.say("Seems like you're getting it!")
                goto(Idle)
            }
        } else {
            goto(StudentNotRead)
        }

    }
    onReentry {
        furhat.say("Seems like you're still having some trouble with the exercises")
        call(UnderstandingFigure)
        call(UnderstandingNextStep)
    }
}



val StudentNotRead: State = state(Parent){
    onEntry {
        furhat.attend(users.random)
        furhat.ask("Can you read me the question word for word for me please")
    }
    onReentry {
        furhat.ask("That wasn't quite right, but it could also be that I didn't understand you correctly. Would you please try again?")
    }
    onResponse<ReadQuestionWell> {
        read_exercise = true
        println(read_exercise)
        var continue_on = furhat.askYN("Very well! Do you think you can continue on by yourself for now?")
        if (continue_on) {
            furhat.say("Great! Maybe try some different approaches or explore the task some more. Let me know if you need any more help!")
            goto(Idle)
        } else {
            furhat.say("Alright, I'll help you explore the question a little bit!")
            call(StudentReadWellStuck)
            terminate()


        }

    }
    onResponse {
        reentry()
    }
}



val UnderstandingFigure: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)
        furhat.say("Let's see if you have understood the figure")
        furhat.ask("Could you tell me how many squares you can see in the picture? Please say so in the following form: I see blank squares in the picture")
    }
    onReentry {
        furhat.attend(users.random)
        furhat.ask("That wasn't quite right. Have another count. After that, please say how many squares you have counted in the following form: I see blank squares in the picture")
    }
    onResponse<Four> {
        furhat.say("Nice, there are indeed 4 squares")
        understands_figure = true
        terminate()
    }
    onResponse{
        reentry()
    }
}


val UnderstandingNextStep: State = state(Parent){
    onEntry {
        furhat.attend(users.random)
        furhat.ask("How many matchsticks do you think you need for the next square? Please answer in the following form: I need blank matchsticks for the next square")
    }
    onReentry {
        furhat.say("That wasn't quite right, no worries! Have another look and try again")
        furhat.ask("So how many matchsticks do you need for the next square?")
    }
    onResponse<Three> {
            furhat.say("Nice, good job!")
            understand_next_step = true
            terminate()
        }
    onResponse {
            reentry()
        }
}

val StudentReadWellStuck: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)
        call(UnderstandingFigure)
        call(UnderstandingNextStep)
    }
}

val StuckAfterFiveSquares: State = state(Parent){
    onEntry {
        if (!understand_next_step){
            call(UnderstandingNextStep)
        } else{
        furhat.say("Lets see if I can help you understand how to continue. So you know how many matches you need to add another square to the existing four.")
        furhat.ask("So let's do the same thing again. If you have 5 squares now, and you want to add another, how many matches do you need?")
    }}
    onResponse<Three> {
        furhat.say("You're getting it! Now try to find the pattern in this. Maybe draw a table, or draw the figure. Do that for a smaller number of squares.")
        furhat.gesture(Gestures.BigSmile)
        furhat.say("You can do this. If you want me to check your solution, just say: Furhat, could you check my solution")
        terminate()
    }
    onResponse {
        furhat.say("That wasn't quite right. Think about how you did the process for  ")
    }
}

val CheckSolutionState: State = state(Parent){
    onEntry {
        furhat.ask("Alright, let's hear it! What is your solution?")
    }
    onResponse<CorrectSolution> {
        furhat.say("Nice! Well done! I knew you could do it")
    }
    onResponse {
        furhat.say("Ah that's not quite right, but have another think! Call me when you're ready with a new answer!")
        goto(Idle)
    }

}




//    if (understands_figure == true){
//        furhat.askYN("Have you tried exploring the task a little bit further yet? ")
//
//    }
//    else{
//        furhat.ask("How many squares do you see in the figure? Please answer in the following form: I see blank squares in the picture")
//        onResponse<Four> {
//            furhat.say("Nice, good job!")
//            understands_figure = true
//        }
//        onResponse {
//            furhat.say("That wasn't quite right sadly, have another look an try again")
//            reentry()
//        }
//    }
//    onReentry {
//        if (understands_figure == true){
//            furhat.ask("So how many matches do you think you need for the next square?")
//            onResponse<Three> {
//                furhat.say("Super! You're getting it!")
//                understand_next_step = true
//            }
//        } else{
//            furhat.ask("How many squares do you see in the figure? Please answer in the following form: I see blank squares in the picture")
//            onResponse<Four> {
//                furhat.say("Nice, good job!")
//                understands_figure = true
//                reentry()
//            }
//            onResponse {
//                furhat.say( "That wasn't quite right. Try again!")
//                reentry()
//            }
//        }
//
//
//    }

//}

//            var userInput: String? = null
//
//                // Keep prompting the user until the word "hello" is not in the response
//                while (userInput == null || "4" in userInput) {
//                    furhat.say("Could you tell me how many squares are in the figure?")
//                    userInput = furhat.listen(timeout = 5000).toString()
//                    userInput.lowercase()
//
//                    if (userInput.contains("4")) {
//                        furhat.say("Very good!")
//                        goto(Greeting)
//                    } else {
//                        furhat.say("Please have another look" + Gestures.Smile)
//                    }
//
//                }
//        }



//            onResponse<NoIHaveNot> {
//                var userInput: String? = null
//
//                // Keep prompting the user until the word "hello" is not in the response
//                while (userInput == null || "four" in userInput) {
//                    furhat.say("Could you tell me how many squares are in the figure?")
//                    userInput = furhat.listen(timeout = 5000).toString()
//                    userInput.lowercase()
//
//                    if ("hello" in userInput) {
//                        furhat.say("Very good!")
//                        goto(Greeting)
//                    } else {
//                        furhat.say("Please have another look" + Gestures.Smile)
//                    }
//
//                }
//            }
//
//            furhat.say("You did not say 'hello'.")
//        }
//    }
//    onResponse<NoIHaveNot> {
//        furhat.ask("Could you read the question out loud for me and see if that clears it up for you?")
//        onResponse<ReadQuestionWell> {
//            furhat.ask("Do you understand every part of the question?")
//            onResponse<NoIHaveNot> {
//                furhat.ask("What have you not understood?")
//                onResponse<StuckAtStart> { }
//            }
//        }
//    }
//}