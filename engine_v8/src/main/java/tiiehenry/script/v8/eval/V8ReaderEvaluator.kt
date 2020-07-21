package tiiehenry.script.v8.eval

import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IReaderEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable
import java.io.Reader

class V8ReaderEvaluator(private val engine: V8Engine, override val context: IScriptContext = engine.context)
    : IV8Evaluator<Reader>, IReaderEvaluator<Any, V8Type> {
    override fun eval(input: Reader, scriptName: String?, lineNumber: Int): IVariable<Any, V8Type>? {
       return engine.stringEvaluator.eval(input.readText(),scriptName, lineNumber)
    }
}