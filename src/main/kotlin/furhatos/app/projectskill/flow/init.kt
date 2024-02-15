package furhatos.app.projectskill.flow

import furhatos.app.projectskill.flow.main.Idle
import furhatos.app.projectskill.flow.main.Greeting
import furhatos.app.projectskill.setting.DISTANCE_TO_ENGAGE
import furhatos.app.projectskill.setting.MAX_NUMBER_OF_USERS
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.records.Faceprint

val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)

        /** Character Params */

        furhat.character = "Alex"
//        furhat.voice = Voice("Matthew-neural")
    }
    onEntry {
        /** start interaction */
        goto(Greeting)
//        when {
//            furhat.isVirtual() -> goto(Greeting) // Convenient to bypass the need for user when running Virtual Furhat
//            users.hasAny() -> {
//                furhat.attend(users.random)
//                goto(Greeting)
//            }
//            else -> goto(Idle)
//        }
    }

}
