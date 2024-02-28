package furhatos.app.projectskill.flow.main

import furhatos.flow.kotlin.*
import furhatos.app.projectskill.nlu.*
import furhatos.nlu.common.Yes
import furhatos.nlu.common.No
import furhatos.app.projectskill.flow.Parent


val StartHelp: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)

        var started = furhat.askYN("Have you tried solving the exercise yet?")
        if (started) {
            furhat.say("Okay, let's see where I can help you!")
            goto(StudentMisinterpretation)
        } else {
            furhat.say("That's not good. Let's read the exercise together first")
        }
    }
}





