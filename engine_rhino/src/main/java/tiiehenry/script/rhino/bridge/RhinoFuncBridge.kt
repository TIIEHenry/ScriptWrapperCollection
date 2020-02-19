package tiiehenry.script.rhino.bridge

import org.mozilla.javascript.Function
import org.mozilla.javascript.RhinoException
import tiiehenry.script.engine.bridge.FuncBridge
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.rhino.RhinoEngine

class RhinoFuncBridge(engine: RhinoEngine) : FuncBridge<Function, RhinoEngine>(engine) {

    override fun callFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null
        try {
            val newArgs = arrayOfNulls<Any?>(args.size)
            for (i in args.indices) {
                newArgs[i] = engine.varBridge.javaToJS(args[i])
            }
            result = func.call(engine.context, engine.runtime, engine.runtime, newArgs)
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
            engine.varBridge.toString(it)
        }
    }

    override fun callIntegerFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Int? {
        return callFuncL(func, listener, args)?.let {
            engine.varBridge.toInteger(it)
        }
    }

    override fun callFloatFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Float? {
        return callDoubleFuncL(func, listener, args)?.toFloat()
    }

    override fun callDoubleFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Double? {
        return callFuncL(func, listener, args)?.let {
            engine.varBridge.toDouble(it)
        }
    }

    override fun callBooleanFuncL(func: Function, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return callFuncL(func, listener, args)?.let {
            engine.varBridge.toBoolean(it)
        }
    }


    fun getFunc(name: String): Function? {
        return engine.varBridge.getVar(name) as? Function
    }

}