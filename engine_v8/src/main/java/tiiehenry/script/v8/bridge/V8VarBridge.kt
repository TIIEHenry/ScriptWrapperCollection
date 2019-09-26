package tiiehenry.script.v8.bridge

import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import com.eclipsesource.v8.V8Value
import tiiehenry.script.v8.V8Engine
import tiiehenry.script.engine.bridge.VarBridge

class V8VarBridge(engine: V8Engine) : VarBridge<Int, V8Engine>(engine) {


    override fun getType(name: String): Int {
        return engine.runtime.getType(name)
    }

    override fun getVar(name: String): Any? {
        return engine.runtime.get(name)
    }

    override fun getString(name: String): String? {
        return engine.runtime.getString(name)
    }

    override fun getBoolean(name: String): Boolean? {
        return engine.runtime.getBoolean(name)
    }

    override fun getInteger(name: String): Int? {
        return engine.runtime.getInteger(name)
    }

    override fun getFloat(name: String): Float? {
        return getDouble(name)?.toFloat()
    }

    override fun getDouble(name: String): Double? {
        return engine.runtime.getDouble(name)
    }

    fun getArray(name: String): V8Array? {
        return engine.runtime.getArray(name)
    }

    fun getObject(name: String): V8Object? {
        return engine.runtime.getObject(name)
    }

    override fun putVar(name: String, value: Any?) {
        if (value != null) {
            if (value is V8Value)
                engine.runtime.add(name, value)
        } else {
            engine.runtime.addNull(name)
        }
    }

    override fun putString(name: String, value: String) {
        engine.runtime.add(name, value)
    }

    override fun putBoolean(name: String, value: Boolean) {
        engine.runtime.add(name, value)
    }

    override fun putInteger(name: String, value: Int) {
        engine.runtime.add(name, value)
    }

    override fun putFloat(name: String, value: Float) {
        engine.runtime.add(name, value.toDouble())
    }

    override fun putDouble(name: String, value: Double) {
        engine.runtime.add(name, value)
    }

    fun putArray(name: String, value: V8Array) {
        engine.runtime.add(name, value)
    }

    fun putObject(name: String, value: V8Object) {
        engine.runtime.add(name, value)
    }
}