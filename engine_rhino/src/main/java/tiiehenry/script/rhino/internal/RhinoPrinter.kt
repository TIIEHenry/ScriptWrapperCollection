package tiiehenry.script.rhino.internal

import android.util.Log
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.internal.IPrinter

class RhinoPrinter(
    override val engine: RhinoEngine,
    override val context: IScriptContext = engine.context
) : IPrinter<Any, RhinoType> {

    override fun print(msg: Any?) {
        if (msg == null) {
            context.output.print("null")
        } else {
            context.output.print(RhinoVariable(msg).getString() ?: "null")
        }
//        msg?.let {
//        }
    }

}
