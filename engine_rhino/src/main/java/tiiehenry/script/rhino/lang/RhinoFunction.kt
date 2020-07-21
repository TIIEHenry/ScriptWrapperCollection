package tiiehenry.script.rhino.lang

import org.mozilla.javascript.Function
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.wrapper.engine.lang.IFunction
import tiiehenry.script.wrapper.engine.lang.IVariable

class RhinoFunction(private val engine: RhinoEngine, private val func: Function) : IFunction<RhinoType> {
    override fun call(vararg args: Any): IVariable<*, RhinoType>? {
        val newArgs = arrayOfNulls<Any?>(args.size)
        for (i in args.indices) {
            newArgs[i] = engine.varBridge.javaToJS(args[i])
        }
        val ret = func.call(engine.runtime.rhinoContext, engine.runtime, engine.runtime, newArgs) ?: return null
        return RhinoVariable(ret)
    }
}