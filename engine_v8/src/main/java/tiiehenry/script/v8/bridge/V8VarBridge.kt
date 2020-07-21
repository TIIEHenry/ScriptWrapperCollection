package tiiehenry.script.v8.bridge

import com.eclipsesource.v8.V8Object
import com.eclipsesource.v8.V8Value
import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.v8.lang.V8LazyVariable
import tiiehenry.script.wrapper.engine.bridge.IVarBridge
import tiiehenry.script.wrapper.engine.lang.IVariable

class V8VarBridge(private val engine: V8Engine) : IVarBridge<Any, V8Type> {
    override fun get(name: String): IVariable<Any, V8Type>? {
        return V8LazyVariable(engine.runtime, name)
    }

    override fun set(name: String, value: Any?, type: V8Type?) {
        if (value == null) {
            engine.runtime.addNull(name)
            return
        }
        val t = type
                ?: when (value) {
                    is String -> V8Type.String
                    is Int -> V8Type.Integer
                    is Double -> V8Type.Double
                    is Boolean -> V8Type.Boolean
                    is V8Value -> V8Type.V8Object
                    else -> null
                }

        if (t == null) {
            throw RuntimeException("can't set value: type unsupported")
        } else {
            when (t) {
                V8Type.String -> {
                    engine.runtime.add(name, value as String)
                }
                V8Type.Integer -> {
                    engine.runtime.add(name, value as Int)
                }
                V8Type.Double -> {
                    engine.runtime.add(name, value as Double)
                }
                V8Type.Boolean -> {
                    engine.runtime.add(name, value as Boolean)
                }
                V8Type.V8Object -> {
                    engine.runtime.add(name, value as V8Object)
                }
                else -> {
                }
            }
        }
    }
}