package tiiehenry.script.rhino.eval

import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.engine.eval.StringEvaler
import tiiehenry.script.rhino.RhinoEngine

class RhinoStringEvaler(engine: RhinoEngine) : StringEvaler<RhinoEngine>(engine) {
    val runtime = engine.runtime
    val context = engine.context

    override fun evalString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Any? {
        try {
            return context.evaluateString(runtime, code, scriptName, 1, null)
        }catch (e: Exception) {
            listener.onException(e)
        }
        return null
    }

    override fun evalVoidString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener) {
        evalString(code, scriptName, lineNumber, listener)
    }

    override fun evalStringString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): String? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            engine.varBridge.toString(it)
        }
    }

    override fun evalIntegerString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Int? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            engine.varBridge.toInteger(it)
        }
    }

    override fun evalFloatString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Float? {
        return evalDoubleString(code, scriptName, lineNumber, listener)?.let {
            engine.varBridge.toFloat(it)
        }
    }

    override fun evalDoubleString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Double? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            engine.varBridge.toDouble(it)
        }
    }

    override fun evalBooleanString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Boolean? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            engine.varBridge.toBoolean(it)
        }
    }


}