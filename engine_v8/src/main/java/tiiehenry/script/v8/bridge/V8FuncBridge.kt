package tiiehenry.script.v8.bridge

import com.eclipsesource.v8.V8Function
import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Func
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.bridge.IFuncBridge
import tiiehenry.script.wrapper.engine.lang.IFunction
import java.util.*

class V8FuncBridge(private val engine: V8Engine, override val context: IScriptContext = engine.context) : IFuncBridge<V8Type> {

    override fun get(name: String): IFunction<V8Type>? {
        val func = engine.varBridge.get(name) as? V8Function ?: return null
        return V8Func(engine, func)
    }

    override fun set(name: String, function: IFunction<V8Type>?) {
        engine.runtime.registerJavaMethod(function, "call", name, arrayOf(Any::class.java))
    }

}