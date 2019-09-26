package tiiehenry.script.rhino.bridge

import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import tiiehenry.script.engine.bridge.FuncBridge
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.rhino.RhinoEngine

class RhinoFuncBridge(engine: RhinoEngine) : FuncBridge<Function, RhinoEngine>(engine) {

    override fun callFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null
        try {
            result = func.call(engine.context, engine.runtime, engine.runtime, args)
        } catch (e: Exception) {
            listener.onException(e)
        }
        return result
    }

    override fun callVoidFuncL(func: Function, listener: OnExceptionListener, vararg args: Any) {
        callFuncL(func, listener, args)
    }

    override fun callStringFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): String? {
        return callFuncL(func, listener, args)?.let {
            Context.jsToJava(it, String::class.java) as? String?
        }
    }

    override fun callIntegerFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Int? {
        return callFuncL(func, listener, args)?.let {
            Context.jsToJava(it, Int::class.java) as? Int?
        }
    }

    override fun callFloatFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Float? {
        return callDoubleFuncL(func, listener, args)?.toFloat()
    }

    override fun callDoubleFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Double? {
        return callFuncL(func, listener, args)?.let {
            Context.jsToJava(it, Double::class.java) as? Double?
        }
    }

    override fun callBooleanFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return callFuncL(func, listener, args)?.let {
            Context.jsToJava(it, Boolean::class.java) as? Boolean?
        }
    }

    val runtime = engine.runtime


    fun getFunc(name: String): Function? {
        return engine.varBridge.getVar(name) as? Function
    }

}