package tiiehenry.script.rhino.bridge

import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoFunction
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.bridge.IFuncBridge
import tiiehenry.script.wrapper.framework.lang.IFunction

class RhinoFuncBridge(private val engine: RhinoEngine, override val context: IScriptContext = engine.context) : IFuncBridge<RhinoType> {

    override fun get(name: String): RhinoFunction? {
        val func = engine.varBridge.get(name)?.getFunction() ?: return null
        return RhinoFunction(engine, func)
    }

    override fun set(name: String, function: IFunction<RhinoType>?) {
        throw RuntimeException("can't set function")
//        engine.runtime.defineFunctionProperties()
    }

}