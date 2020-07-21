package tiiehenry.script.rhino.internal

import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.IScriptEngine
import tiiehenry.script.wrapper.engine.internal.InputEvaluateTask
import java.io.InputStream

class RhinoInputEvaluateTask(input: InputStream, engine: IScriptEngine<Any, RhinoType>) : InputEvaluateTask<Any, RhinoType>(input, engine) {
    override fun shouldFinish(str: String?): Boolean {
        return str == "exit"
    }
}