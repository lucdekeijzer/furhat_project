package furhatos.app.projectskill.flow.main

import furhatos.app.projectskill.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting: State = state(Parent) {
    onEntry {
        furhat.say("Hi, my name is Furhat. That is because of my furry hat, which keeps me nice and cozy")
        furhat.ask("Would you like help with your exercises?")
    }

    onReentry {
        furhat.say("Welcome back!")
        furhat.ask("Would you like help with your exercises?")
    }

    onResponse<Yes> {
        furhat.say("Okay, here we go!")
        goto(StartHelp)
    }

    onResponse<No> {
        furhat.say("Ok.")
        goto(Idle)
    }

}

