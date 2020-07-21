package tiiehenry.script.rhino.eval

import org.mozilla.javascript.RhinoException
import tiiehenry.script.rhino.lang.RhinoExceptionWrapper
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.framework.evaluate.IEvaluator
import tiiehenry.script.wrapper.framework.lang.IVariable

interface IRhinoEvaluator<S> : IEvaluator<Any, RhinoType, S> {

    override fun evalSafely(input: S, scriptName: String?, lineNumber: Int): IVariable<Any, RhinoType>? {
        try {
            return eval(input,  scriptName, lineNumber)
        } catch (e: RhinoException) {
            context.error.println(RhinoExceptionWrapper(e, scriptName, lineNumber).message)
        } catch (e: Exception) {
            context.error.println(e.message)
        }
        return null
    }
}