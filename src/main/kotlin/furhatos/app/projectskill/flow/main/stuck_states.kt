package furhatos.app.projectskill.flow.main

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

var understands_figure = false
var read_exercise = false

val StudentNotRead: State = state(Parent){
    onEntry {
        furhat.attend(users.random)
        furhat.say("Let's read the exercise together shall we.")
        furhat.ask("Can you read me the question word for word for me please" + Gestures.BigSmile)
    }
    onResponse<ReadQuestionWell> {
        read_exercise = true
        var continue_on = furhat.askYN("Very well! Do you think you can continue on by yourself for now? Maybe try some different approaches or explore the task some more")
        if (continue_on) {
            furhat.say("Great! Let me know if you need any more help!" + Gestures.BigSmile)
            goto(Idle)
        } else {
            furhat.say("Alright, I'll help you explore the question a little bit")

        }

}}


val StudentMisinterpretation: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)
        furhat.say("Let's see if you have understood the exercise correctly")
        furhat.ask("First of all, have you read the exercise?")
    }

    // check for two response types
    onResponse<Yes> {
//        read_exercise = true
        var understood = furhat.askYN("Are you sure you understood the question?")
        if (understood) {
            furhat.say("Good to hear!")
            goto(StudentReadWellStuck)
        } else {
            furhat.ask("Could you tell me how many squares are in the figure?")
            onResponse<StuckReadWell> {
                furhat.say("Very good! The NLU works")
            }
    onResponse<No> {
        furhat.say("Why don't we start with that.")
        furhat.ask("Can you just read out the question word for word for me please?")
        onResponse<ReadQuestionWell> {

            var understood = furhat.askYN("Very good. Are you sure you understood the question?" + Gestures.Smile)
            if (understood) {
                furhat.say("Good to hear!")
                var more_help = furhat.askYN("Are you sure you understood the question?")
                if (more_help) {
                    goto(StudentReadWellStuck)
                } else {
                    furhat.ask("Could you tell me how many squares are in the figure?")
                    furhat.say("This is where the 4 thing goes.")
                    understands_figure = true
                }
            } else {
                furhat.ask("Could you tell me how many squares are in the figure?")
                furhat.say("This is where the 4 thing goes.")
            }
        }
    }
        }
    }
}

val StudentReadWellStuck: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)
        furhat.say("Hi there, this is the next state")
        if (understands_figure == true){
            furhat.askYN("Have you tried exploring the task a little bit further yet? ")

        }
    }

    if (understands_figure == true){



    }
    else {

    }
}

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