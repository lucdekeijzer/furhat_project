package furhatos.app.projectskill.flow.main

import furhatos.flow.kotlin.*
import furhatos.app.projectskill.nlu.*
import furhatos.nlu.common.Yes
import furhatos.nlu.common.No
import furhatos.app.projectskill.flow.Parent


val StartHelp: State = state(Parent) {
    onEntry {
        furhat.attend(users.random)

        /** First classification of student in decision tree */
        furhat.ask("Let's see if I can find out what you are struggling with. Have you tried to solve the problem yet?")
        onResponse<Yes> {
            furhat.say("Okay, let's see where I can help you!")
            goto(StudentMisinterpretation)
            }
        }
    }





