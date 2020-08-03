package tiiehenry.script.rhino.eval

import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.evaluate.IFileEvaluator
import java.io.File
import java.io.FileReader

class RhinoFileEvaluator(
    private val engine: RhinoEngine,
    override val context: IScriptContext = engine.context
) : IRhinoEvaluator<File>, IFileEvaluator<Any, RhinoType> {

    override fun eval(input: File, scriptName: String?, lineNumber: Int): RhinoVariable? {
        FileReader(input).use { return engine.readerEvaluator.eval(it, scriptName, lineNumber) }
    }
}