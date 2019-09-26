package tiiehenry.script.androlua.eval

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.eval.FuncEvaler
import tiiehenry.script.engine.eval.OnExceptionListener

class ALuaFuncEvaler(engine: ALuaEngine) : FuncEvaler<ALuaEngine>(engine) {
    val runtime = engine.runtime

    override fun evalFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null
        try {
            result = engine.funcBridge.callFunc(name,listener,args)
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
            it as? String?
        }
    }

    override fun evalIntegerFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Int? {
        return evalFunc(name, args, listener)?.let {
            it as? Int?
        }
    }

    override fun evalFloatFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Float? {
        return evalFunc(name, args, listener)?.let {
            it as? Float?
        }
    }

    override fun evalDoubleFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Double? {
        return evalFunc(name, args, listener)?.let {
            it as? Double?
        }
    }

    override fun evalBooleanFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Boolean? {
        return evalFunc(name, args, listener)?.let {
            it as? Boolean?
        }
    }


}