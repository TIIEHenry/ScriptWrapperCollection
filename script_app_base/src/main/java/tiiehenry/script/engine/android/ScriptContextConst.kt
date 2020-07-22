package tiiehenry.script.engine.android

class ScriptContextConst {
    companion object {
        object INTENT {
            const val ENGINE_FACTORY_NAME = "ENGINE_FACTORY_NAME"
            const val SCRIPT_BEFORE_DATA = "SCRIPT_BEFORE_DATA"
            const val SCRIPT_AFTER_DATA = "SCRIPT_AFTER_DATA"
        }

        object HANDLER {
            val LOG = "LOG".hashCode()
            val TOAST = "TOAST".hashCode()
            val PRINT = "PRINT".hashCode()
            val PRINTLN = "PRINTLN".hashCode()
        }
    }
}