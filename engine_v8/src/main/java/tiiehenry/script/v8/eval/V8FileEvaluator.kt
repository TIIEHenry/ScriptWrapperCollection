package tiiehenry.script.v8.eval

import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IFileEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable
import java.io.File
import java.io.FileReader

class V8FileEvaluator(private val engine: V8Engine, override val context: IScriptContext = engine.context)
    : IV8Evaluator<File>, IFileEvaluator<Any, V8Type> {

    override fun eval(input: File, scriptName: String?, lineNumber: Int): IVariable<Any, V8Type>? {
        FileReader(input).use { return engine.readerEvaluator.eval(it, scriptName, lineNumber) }
    }
}