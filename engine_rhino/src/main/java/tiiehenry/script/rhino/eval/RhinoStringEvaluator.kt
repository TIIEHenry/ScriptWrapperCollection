package tiiehenry.script.rhino.eval

import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IStringEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable

class RhinoStringEvaluator(private val engine: RhinoEngine, override val context: IScriptContext = engine.context) : IRhinoEvaluator<String>, IStringEvaluator<Any, RhinoType> {


    override fun eval(input: String, scriptName: String?, lineNumber: Int): IVariable<Any, RhinoType>? {
        val ret = engine.runtime.rhinoContext.evaluateString(engine.runtime, input, scriptName, lineNumber, null)
                ?: return null
        return RhinoVariable(ret)
    }

}