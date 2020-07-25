package tiiehenry.script.androlua.lang

import tiiehenry.script.androlua.internal.ALuaRuntime
import tiiehenry.script.wrapper.framework.lang.IFunction
import tiiehenry.script.wrapper.framework.lang.IVariable

class ALuaFunction(private val runtime: ALuaRuntime, private val name: String) : IFunction<ALuaType> {
    override fun call(vararg args: Any): IVariable<*, ALuaType>? {
        val L = runtime.L
        synchronized(L) {
            L.top = 0
            L.pushGlobalTable()
            L.pushString(name)
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
                    return ALuaVariable(L.toJavaObject(-1))
                }
                throw Exception(runtime.errorReason(ok) + ": " + L.toString(-1))
            }

        }
        return null
    }
}