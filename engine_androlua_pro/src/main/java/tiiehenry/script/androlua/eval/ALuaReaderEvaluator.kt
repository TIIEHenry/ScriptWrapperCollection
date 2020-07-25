package tiiehenry.script.androlua.eval

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.androlua.lang.ALuaType
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IReaderEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable
import java.io.Reader

class ALuaReaderEvaluator(private val engine: ALuaEngine, override val context: IScriptContext = engine.context) : IALuaEvaluator<Reader>, IReaderEvaluator<Any, ALuaType> {
    override fun eval(input: Reader, scriptName: String?, lineNumber: Int): IVariable<Any, ALuaType>? {
        return engine.stringEvaluator.eval(input.readText(), scriptName, lineNumber)
    }

}