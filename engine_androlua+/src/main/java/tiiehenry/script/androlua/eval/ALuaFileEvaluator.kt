package tiiehenry.script.androlua.eval

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.androlua.lang.ALuaType
import tiiehenry.script.androlua.lang.ALuaVariable
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.evaluate.IFileEvaluator
import tiiehenry.script.wrapper.engine.lang.IVariable
import java.io.File

class ALuaFileEvaluator(private val engine: ALuaEngine, override val context: IScriptContext = engine.context) : IALuaEvaluator<File>, IFileEvaluator<Any, ALuaType> {

    override fun eval(input: File, scriptName: String?, lineNumber: Int): IVariable<Any, ALuaType>? {
        val L = engine.runtime.L
        L.top = 0
        var ok = L.LloadFile(input.absolutePath)

        if (ok == 0) {
            L.getGlobal("debug")
            L.getField(-1, "traceback")
            L.remove(-2)
            L.insert(-2)
            val args = listOf<String>()
            val l = args.size
            for (i in 0 until l) {
                L.pushObjectValue(args[i])
            }
            ok = L.pcall(l, 1, -2 - l)
            if (ok == 0) {
                return ALuaVariable(L.toJavaObject(-1))
            }
        }
        throw Exception(engine.runtime.errorReason(ok) + ": " + L.toString(-1))
//        return null
    }
}
