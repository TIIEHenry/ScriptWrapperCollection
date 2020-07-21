package tiiehenry.script.rhino.eval

import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.evaluate.IFileEvaluator
import tiiehenry.script.wrapper.engine.lang.IVariable
import java.io.File
import java.io.FileReader

class RhinoFileEvaluator(private val engine: RhinoEngine, override val context: IScriptContext = engine.context) : IRhinoEvaluator<File>, IFileEvaluator<Any, RhinoType> {

    override fun eval(input: File,  scriptName: String?, lineNumber: Int): IVariable<Any, RhinoType>? {
        return engine.readerEvaluator.eval(FileReader(input),  scriptName, lineNumber)
    }
}