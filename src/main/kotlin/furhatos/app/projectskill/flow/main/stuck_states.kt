package furhatos.app.projectskill.flow.main

import furhatos.app.projectskill.flow.Parent
import furhatos.app.projectskill.nlu.NoIHaveNot
import furhatos.app.projectskill.nlu.ReadQuestionWell
import furhatos.app.projectskill.nlu.StuckAtStart
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.Intent.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.gestures.Gestures


val StudentMisinterpretation: State = state(Parent) {
    onEntry {
        furhat.say("Let's see if you have understood the exercise correctly")
        furhat.ask("First of all, have you read the exercise?")
    }
    onResponse<Yes> {
        furhat.ask("Are you sure you understood the question?")
        onResponse<Yes> {
            furhat.ask("Have you understood the figure that is on the paper?")
            onResponse<Yes> {
                goto(Greeting)
            }
            onResponse<NoIHaveNot> {
                var userInput: String? = null

                // Keep prompting the user until the word "hello" is not in the response
                while (userInput == null || "four" in userInput) {
                    furhat.say("Could you tell me how many squares are in the figure?")
                    userInput = furhat.listen(timeout = 5000).toString()
                    userInput.lowercase()

                    if ("hello" in userInput) {
                        furhat.say("Very good!")
                        goto(Greeting)
                    } else {
                        furhat.say("Please have another look" + Gestures.Smile)
                    }

                }
            }

            furhat.say("You did not say 'hello'.")
        }
    }
    onResponse<NoIHaveNot> {
        furhat.ask("Could you read the question out loud for me and see if that clears it up for you?")
        onResponse<ReadQuestionWell> {
            furhat.ask("Do you understand every part of the question?")
            onResponse<NoIHaveNot> {
                furhat.ask("What have you not understood?")
                onResponse<StuckAtStart> { }
            }
        }
    }
}