package furhatos.app.projectskill.flow.main
import furhatos.app.projectskill.nlu.FurhatCheckSolution
import furhatos.app.projectskill.nlu.FurhatNeedHelp
import kotlin.random.Random
import furhatos.app.projectskill.nlu.ReadQuestionWell
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures


val Idle: State = state {
    onEntry {
        furhat.attendNobody()
        var random_int = Random.nextInt(90000, 120000)
        furhat.listen(timeout = random_int)
    }

    onReentry {
        var random_int = Random.nextInt(90000, 120000)
        furhat.listen(timeout = random_int)
    }

    onNoResponse {
        var more_help = furhat.askYN("How is the exercise going? Do you need any help?")
        furhat.gesture(Gestures.BigSmile)
        if (more_help){
            furhat.say("Alright let's see where I can help")
            goto(StartHelp)
        } else{
            furhat.say("Amazing! Good job! If you need me, just call my name and ask for help!")
            reentry()
        }
    }

    onResponse<FurhatNeedHelp>{
        furhat.attend(users.random)
        furhat.say("Hey there! I'd be glad to help you!")
        call(StartHelp)
    }

    onResponse<FurhatCheckSolution>{
        furhat.attend(users.random)
        goto(CheckSolutionState)
    }

//    onUserEnter {
//        furhat.attend(it)
//        goto(Greeting)
//    }

}
