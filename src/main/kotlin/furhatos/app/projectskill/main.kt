package furhatos.app.projectskill

import furhatos.app.projectskill.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class ProjectskillSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
