package furhatos.app.projectskill.flow.main

import furhatos.flow.kotlin.*
import furhatos.app.projectskill.nlu.*
import furhatos.nlu.common.Yes

val StartHelp: State = state {
    onEntry {
        furhat.attend(users.random)

        /** First classification of student in decision tree */
        furhat.ask("What are you struggling with?")
    }
    onResponse<StuckAtStart> {
        furhat.say("Okay, let's see where I can help you!")
        furhat.ask("Could you read out the question for me please?")
        onResponse<StuckReadWell>{
            furhat.say("Very good, let's go to a greeting now")
            goto(Greeting)
        }
        furhat.say("Oh I didn't get that, let's go to a greeting now")
        goto(Greeting)

    }

    }

