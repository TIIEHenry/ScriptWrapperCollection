package tiiehenry.script.androlua.eval

import com.luajava.LuaException
import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.eval.FileEvaler
import tiiehenry.script.engine.eval.OnExceptionListener
import java.io.File

class ALuaFileEvaler(engine: ALuaEngine) : FileEvaler<ALuaEngine>(engine) {


    val L = engine.runtime.L

    fun evalFileForArgs(file: File, listener: OnExceptionListener, vararg args: Any): Any? {
        try {
            L.top = 0
            var ok = L.LloadFile(file.absolutePath)

            if (ok == 0) {
                L.getGlobal("debug")
                L.getField(-1, "traceback")
                L.remove(-2)
                L.insert(-2)
                val l = args.size
                for (i in 0 until l) {
                    L.pushObjectValue(args[i])
                }
                ok = L.pcall(l, 1, -2 - l)
                if (ok == 0) {
                    return L.toJavaObject(-1)
                }
            }
            throw Exception(engine.runtime.errorReason(ok) + ": " + L.toString(-1))
        } catch (e: LuaException) {
            listener.onException(e)
        }

        return null
    }

    override fun evalFile(file: File, listener: OnExceptionListener): Any? {
        return evalFileForArgs(file, listener)
    }


    override fun evalVoidFile(file: File, listener: OnExceptionListener) {
        evalFile(file, listener)
    }


    override fun evalStringFile(file: File, listener: OnExceptionListener): String? {
        return evalFile(file, listener)?.let {
            it as? String?
        }
    }
    

    override fun evalIntegerFile(file: File, listener: OnExceptionListener): Int? {
        return evalFile(file, listener)?.let {
            it as? Int?
        }
    }


    override fun evalFloatFile(file: File, listener: OnExceptionListener): Float? {
        return evalFile(file, listener)?.let {
            it as? Float?
        }
    }


    override fun evalDoubleFile(file: File, listener: OnExceptionListener): Double? {
        return evalFile(file, listener)?.let {
            it as? Double?
        }
    }


    override fun evalBooleanFile(file: File, listener: OnExceptionListener): Boolean? {
        return evalFile(file, listener)?.let {
            it as? Boolean?
        }
    }

}