package tiiehenry.script.androlua.bridge

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.bridge.FuncBridge
import tiiehenry.script.engine.eval.OnExceptionListener

class ALuaFuncBridge(engine: ALuaEngine) : FuncBridge<String, ALuaEngine>(engine) {

    val L = engine.runtime.L
    override fun callFuncL(func: String, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null

        synchronized(L) {
            try {
                L.top = 0
                L.pushGlobalTable()
                L.pushString(func)
                L.rawGet(-2)
                if (L.isFunction(-1)) {
                    L.getGlobal("debug")
                    L.getField(-1, "traceback")
                    L.remove(-2)
                    L.insert(-2)

                    val l = args.size
                    for (i in 0 until l) {
                        L.pushObjectValue(args[i])
                    }

                    val ok = L.pcall(l, 1, -2 - l)
                    if (ok == 0) {
                        result = L.toJavaObject(-1)
                    }
                    throw Exception(engine.runtime.errorReason(ok) + ": " + L.toString(-1))
                }
            } catch (e: Exception) {
                listener.onException(e)
            }
        }

        return result
    }

    override fun callVoidFuncL(func: String, listener: OnExceptionListener, vararg args: Any) {
        callFuncL(func, listener, args)
    }

    override fun callStringFuncL(func: String, listener: OnExceptionListener, vararg args: Any): String? {
        return callFuncL(func, listener, args)?.let {
            it as? String?
        }
    }

    override fun callIntegerFuncL(func: String, listener: OnExceptionListener, vararg args: Any): Int? {
        return callFuncL(func, listener, args)?.let {
            it as? Int?
        }
    }

    override fun callFloatFuncL(func: String, listener: OnExceptionListener, vararg args: Any): Float? {
        return callFuncL(func, listener, args)?.let {
            it as? Float?
        }
    }

    override fun callDoubleFuncL(func: String, listener: OnExceptionListener, vararg args: Any): Double? {
        return callFuncL(func, listener, args)?.let {
            it as? Double?
        }
    }

    override fun callBooleanFuncL(func: String, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return callFuncL(func, listener, args)?.let {
            it as? Boolean?
        }
    }


}