package tiiehenry.script.rhino.bridge

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.rhino.lang.RhinoVariable
import tiiehenry.script.wrapper.engine.bridge.IVarBridge
import tiiehenry.script.wrapper.engine.lang.IVariable

class RhinoVarBridge(private val engine: RhinoEngine) : IVarBridge<Any, RhinoType> {


    fun jsToJava(value: String, clazz: Class<*>): Any? {
        return Context.jsToJava(get(value), clazz)
    }

    fun jsToJava(value: String, clazz: Class<*>, f: (Any?) -> Unit) {
        f.invoke(Context.jsToJava(get(value), clazz))
    }

    fun jsToJava(value: Any, clazz: Class<*>): Any? {
        return Context.jsToJava(value, clazz)
    }

    fun javaToJS(value: Any?): Any? {
        return Context.javaToJS(value, engine.runtime)
    }

    fun javaToJS(value: String, f: (Any?) -> Unit) {
        f.invoke(Context.javaToJS(get(value), engine.runtime))
    }


    fun toObject(value: Any?): Any? {
        if (value is Scriptable) {
            return Context.jsToJava(value, Any::class.java)
        }
        return value
    }

    override fun get(name: String): RhinoVariable? {
        val v = engine.runtime.get(name) ?: return null
        return RhinoVariable(v)
    }

    override fun set(name: String, value: Any?, type: RhinoType?) {
        engine.runtime.put(name, engine.runtime, if (value == null) value else javaToJS(value))
    }

}