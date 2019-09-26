package tiiehenry.script.androlua.eval

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.engine.eval.StringEvaler

class ALuaStringEvaler(engine: ALuaEngine) : StringEvaler<ALuaEngine>(engine) {

    val runtime = engine.runtime
    val L = engine.runtime.L

   fun evalStringForArgs(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener,vararg args:Any?): Any? {
       try {
           L.top = 0
           var ok = L.LloadString(code)

           if (ok == 0) {
               L.getGlobal("debug")
               L.getField(-1, "traceback")
               L.remove(-2)
               L.insert(-2)

               val l = args.size
               for (arg in args) {
                   L.pushObjectValue(arg)
               }

               ok = L.pcall(l, 1, -2 - l)
               if (ok == 0) {
                   return L.toJavaObject(-1)
               }
           }
           throw Exception(runtime.errorReason(ok) + ": " + L.toString(-1))
       } catch (e: Exception) {
           listener.onException(e)
       }

       return null
    }
    override fun evalString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Any? {
        return evalStringForArgs(code, scriptName,lineNumber,listener)
    }

    override fun evalVoidString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener) {
        evalString(code, scriptName, lineNumber, listener)
    }

    override fun evalStringString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): String? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            it as? String?
        }
    }

    override fun evalIntegerString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Int? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            it as? Int?
        }
    }

    override fun evalFloatString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Float? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            it as? Float?
        }
    }

    override fun evalDoubleString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Double? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            it as? Double?
        }
    }

    override fun evalBooleanString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Boolean? {
        return evalString(code, scriptName, lineNumber, listener)?.let {
            it as? Boolean?
        }
    }


}