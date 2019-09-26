package tiiehenry.script.rhino.bridge

import org.mozilla.javascript.Context
import org.mozilla.javascript.ScriptableObject
import tiiehenry.script.engine.bridge.VarBridge
import tiiehenry.script.rhino.RhinoEngine

class RhinoVarBridge(engine: RhinoEngine) : VarBridge<RhinoVarBridge.Type, RhinoEngine>(engine) {

    enum class Type {
        String, Boolean, Integer, Float, Double, Object, Null, Undefined
    }

    val runtime = engine.runtime

    fun jsToJava(value: String, clazz: Class<*>): Any? {
        return Context.jsToJava(getVar(value), clazz)
    }

    fun jsToJava(value: String, clazz: Class<*>, f: (Any?) -> Unit) {
        f.invoke(Context.jsToJava(getVar(value), clazz))
    }

    fun javaToJS(value: String): Any? {
        return Context.javaToJS(getVar(value), runtime)
    }

    fun javaToJS(value: String, f: (Any?) -> Unit) {
        f.invoke(Context.javaToJS(getVar(value), runtime))
    }

    override fun getType(name: String): Type {
        return when (val r = getVar(name)) {
            is String -> Type.String
            is Boolean -> Type.Boolean
            is Int -> Type.Integer
            is Float -> Type.Float
            is Double -> Type.Double
            is ScriptableObject -> {
                if ((r as? ScriptableObject)?.typeOf == "object") Type.Object else Type.Undefined
            }
            else -> Type.Null
        }
    }

    override fun getVar(name: String): Any? {
        return runtime.get(name)
    }

    override fun getString(name: String): String? {
        return Context.jsToJava(getVar(name), String::class.java) as? String
    }

    override fun getBoolean(name: String): Boolean? {
        return Context.jsToJava(getVar(name), Boolean::class.java) as? Boolean
    }

    override fun getInteger(name: String): Int? {
        return Context.jsToJava(getVar(name), Int::class.java) as? Int
    }

    override fun getFloat(name: String): Float? {
        return getDouble(name)?.toFloat()
    }

    override fun getDouble(name: String): Double? {
        return Context.jsToJava(getVar(name), Double::class.java) as? Double
    }

    override fun putVar(name: String, value: Any?) {
        runtime.put(name, runtime, value)
    }

    override fun putString(name: String, value: String) {
        putVar(name, value)
    }

    override fun putBoolean(name: String, value: Boolean) {
        putVar(name, value)
    }

    override fun putInteger(name: String, value: Int) {
        putVar(name, value)
    }

    override fun putFloat(name: String, value: Float) {
        putVar(name, value.toDouble())
    }

    override fun putDouble(name: String, value: Double) {
        putVar(name, value)
    }

}