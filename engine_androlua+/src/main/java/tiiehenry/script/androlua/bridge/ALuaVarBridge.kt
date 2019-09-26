package tiiehenry.script.androlua.bridge

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.bridge.VarBridge


class ALuaVarBridge(engine: ALuaEngine) : VarBridge<ALuaVarBridge.Type, ALuaEngine>(engine) {

    val runtime = engine.runtime
    val L = engine.runtime.L

    enum class Type {
        String, Boolean, Integer, Float, Double, Object, Null

    }


    override fun getType(name: String): Type {
        return when (getVar(name)) {
            is String -> Type.String
            is Boolean -> Type.Boolean
            is Int -> Type.Integer
            is Float -> Type.Float
            is Double -> Type.Double
            is Any -> Type.Object
            else -> Type.Null
        }
    }

    override fun getVar(name: String): Any? {
        synchronized(L) {
            L.getGlobal(name)
            return L.toJavaObject(-1)
        }
    }

    override fun getString(name: String): String? {
        return getVar(name)?.let { return it as? String? }
    }

    override fun getBoolean(name: String): Boolean? {
        return getVar(name)?.let { return it as? Boolean? }
    }

    override fun getInteger(name: String): Int? {
        return getVar(name)?.let { return it as? Int? }
    }

    override fun getFloat(name: String): Float? {
        return getVar(name)?.let { return it as? Float? }
    }

    override fun getDouble(name: String): Double? {
        return getVar(name)?.let { return it as? Double? }
    }

    override fun putVar(name: String, value: Any?) {
        synchronized(L) {
            L.pushObjectValue(value)
            L.setGlobal(name)
        }
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
        putVar(name, value)
    }

    override fun putDouble(name: String, value: Double) {
        putVar(name, value)
    }

}