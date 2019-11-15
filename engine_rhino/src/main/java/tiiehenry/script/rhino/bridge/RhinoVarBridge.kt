package tiiehenry.script.rhino.bridge

import org.mozilla.javascript.*
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

    fun jsToJava(value: Any, clazz: Class<*>): Any? {
        return Context.jsToJava(value, clazz)
    }

    fun javaToJS(value: Any?): Any? {
        return Context.javaToJS(value, runtime)
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
        value?.let {
            return runtime.put(name, runtime, javaToJS(it))
        }
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


    override fun toString(value: Any?): String {
        if (value is String)
            return value
/*        val result= when (value) {
            is String -> value
            is NativeJavaObject -> value.getDefaultValue(String::class.java) as? String?
            is NativeObject -> value.getDefaultValue(String::class.java) as? String?
            else ->{
                Context.toString(value)
            }
        }*/
        value?.let {
            return Context.toString(value)
        }
        return "null"
    }

    override fun toBoolean(value: Any?): Boolean {
        if (value is Boolean)
            return value
        value?.let {
            return Context.toBoolean(value)
        }
        return false
    }

    override fun toInteger(value: Any?): Int {
        if (value is Int)
            return value
        value?.let {
            return Context.toNumber(value).toInt()
        }
        return -1
    }

    override fun toFloat(value: Any?): Float {
        if (value is Float)
            return value
        value?.let {
            return Context.toNumber(value).toFloat()
        }
        return -1F
    }

    override fun toDouble(value: Any?): Double {
        if (value is Double)
            return value
        value?.let {
            return Context.toNumber(value)
        }
        return -1.0
    }

    fun toObject(value: Any?): Any? {
        if (value is Scriptable) {
            return Context.jsToJava(value, Any::class.java)
        }
        return value
    }

}