package tiiehenry.script.rhino.internal

import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.annotations.JSFunction
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.internal.IPrinter

class RhinoPrinter(override val engine: RhinoEngine, override val context: IScriptContext = engine.context) :IPrinter<Any, RhinoType> {

    override fun print(msg: Any?) {
        msg?.let {
            context.output.print(RhinoVariable(it).getString() ?: "null")
        }
    }

}
