package furhatos.app.projectskill.flow.main
import furhatos.gestures.Gestures
import furhatos.app.projectskill.flow.Parent
import furhatos.app.projectskill.setting.read_exercise
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting: State = state(Parent) {
    onEntry {
        furhat.say("Hi, my name is Furhat. That is because of my furry hat, which keeps me nice and cozy")
        furhat.gesture(Gestures.Roll, priority = 1)
        furhat.ledStrip.solid(java.awt.Color.BLUE)
        furhat.say("Read through the exercise and try your best to solve it!")
        furhat.say("If you need help with your exercises, I'd be glad to help you! Just say my name and ask for help")
        furhat.ledStrip.solid(java.awt.Color.BLACK)
        goto(Idle)
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
        furhat.say("Ok. If you need me, just say my name and ask for help!")
        goto(Idle)
    }

}

