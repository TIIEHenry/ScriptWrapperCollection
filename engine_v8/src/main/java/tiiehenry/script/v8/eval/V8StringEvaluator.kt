package tiiehenry.script.v8.eval

import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.v8.lang.V8ScriptVariable
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.evaluate.IStringEvaluator
import tiiehenry.script.wrapper.engine.lang.IVariable

class V8StringEvaluator(private val engine: V8Engine, override val context: IScriptContext = engine.context)
    : IV8Evaluator<String>, IStringEvaluator<Any, V8Type> {
    override fun eval(input: String, scriptName: String?, lineNumber: Int): IVariable<Any, V8Type>? {
        return V8ScriptVariable(engine.runtime, input, scriptName, lineNumber)
    }

}