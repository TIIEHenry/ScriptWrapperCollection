package tiiehenry.script.rhino.eval

import org.mozilla.javascript.Context
import tiiehenry.script.engine.eval.FuncEvaler
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.rhino.RhinoEngine

class RhinoFuncEvaler(engine: RhinoEngine) : FuncEvaler<RhinoEngine>(engine) {
    val runtime = engine.runtime

    override fun evalFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null
        try {
            val newArgs = arrayListOf<Any?>()
            for (i in args.indices) {
                newArgs.add(engine.varBridge.javaToJS(args[i]))
            }
            result = engine.funcBridge.getFunc(name)?.call(engine.context, runtime, runtime, newArgs.toArray())
        } catch (e: Exception) {
            listener.onException(e)
        }
        return result
    }

    override fun evalVoidFuncL(name: String, listener: OnExceptionListener, vararg args: Any) {
        evalFunc(name, args, listener)
    }

    override fun evalStringFuncL(name: String, listener: OnExceptionListener, vararg args: Any): String? {
        return evalFunc(name, args, listener)?.let {
            engine.varBridge.toString(it)
        }
    }

    override fun evalIntegerFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Int? {
        return evalFunc(name, args, listener)?.let {
            engine.varBridge.toInteger(it)
        }
    }

    override fun evalFloatFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Float? {
        return evalFunc(name, args, listener)?.let {
            engine.varBridge.toFloat(it)
        }
    }

    override fun evalDoubleFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Double? {
        return evalFunc(name, args, listener)?.let {
            engine.varBridge.toDouble(it)
        }
    }

    override fun evalBooleanFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return evalFunc(name, args, listener)?.let {
            engine.varBridge.toBoolean(it)
        }
    }


}