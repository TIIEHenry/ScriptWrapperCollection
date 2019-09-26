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
            result = engine.funcBridge.getFunc(name)?.call(engine.context, runtime, runtime, args)
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
            Context.jsToJava(it, String::class.java) as? String?
        }
    }

    override fun evalIntegerFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Int? {
        return evalFunc(name, args, listener)?.let {
            Context.jsToJava(it, Int::class.java) as? Int?
        }
    }

    override fun evalFloatFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Float? {
        return evalFunc(name, args, listener)?.let {
            Context.jsToJava(it, Float::class.java) as? Float?
        }
    }

    override fun evalDoubleFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Double? {
        return evalFunc(name, args, listener)?.let {
            Context.jsToJava(it, Double::class.java) as? Double?
        }
    }

    override fun evalBooleanFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return evalFunc(name, args, listener)?.let {
            Context.jsToJava(it, Boolean::class.java) as? Boolean?
        }
    }


}