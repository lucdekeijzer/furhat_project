package furhatos.app.projectskill.flow.main
import furhatos.app.projectskill.setting.*
import furhatos.flow.kotlin.*
import furhatos.app.projectskill.nlu.*
import furhatos.nlu.common.Yes
import furhatos.nlu.common.No
import furhatos.app.projectskill.flow.Parent
import furhatos.app.projectskill.setting.*



val StartHelp: State = state(Parent) {
    onEntry {
        println(read_exercise)
        println(understands_figure)
        println(understand_next_step)
        furhat.attend(users.random)
        if (read_exercise) {
            if (understands_figure) {
                if (understand_next_step) {
                    call(StuckAfterFiveSquares)
                }
            } else {
                call(UnderstandingFigure)
                furhat.say("Try to figure out the next step on your own! I believe in you. I'm always here if you need help though")
                goto(Idle)
            }
        } else {
            var started = furhat.askYN("Have you tried solving the exercise yet?")
            if (started) {
                furhat.say("Okay, let's see where I can help you!")
                goto(StudentMisInterpretation)
            } else {
                furhat.say("That's not good. Let's read the exercise together first")
                goto(StudentNotRead)
            }
        }
    }
    onReentry {

        if (read_exercise){
            if (understands_figure){
                if (understand_next_step){
                    goto(StuckAfterFiveSquares)
                }
            } else{
                goto(UnderstandingFigure)
            }
        } else {
            goto(StudentNotRead)
        }

    }
}





