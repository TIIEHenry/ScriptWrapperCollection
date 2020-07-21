package tiiehenry.script.rhino.eval

import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IReaderEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable
import java.io.Reader

class RhinoReaderEvaluator(private val engine: RhinoEngine, override val context: IScriptContext = engine.context) :  IRhinoEvaluator<Reader>,IReaderEvaluator<Any, RhinoType> {
    override fun eval(input: Reader, scriptName: String?, lineNumber: Int): IVariable<Any, RhinoType>? {
        val ret = engine.runtime.rhinoContext.evaluateReader(engine.runtime, input, scriptName, lineNumber, null)
                ?: return null
        return RhinoVariable(ret)
    }

}