package tiiehenry.script.v8.lang

import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import tiiehenry.script.wrapper.framework.lang.IVariable

class V8LazyVariable(private val runtime: V8, override val value: String) : IVariable<Any, V8Type> {
    override fun getType(): V8Type? {
        return V8Type.valueOf(runtime.getType(value))
    }

    fun get(): Any? {
        return runtime.get(value)
    }

    fun getData(): Any? {
        return runtime.getData(value)
    }

    override fun getInteger(): Int? {
        return runtime.getInteger(value)
    }

    override fun getBoolean(): Boolean? {
        return runtime.getBoolean(value)
    }

    override fun getDouble(): Double? {
        return runtime.getDouble(value)
    }

    override fun getString(): String? {
        return runtime.getString(value)
    }

    fun getArray(): V8Array? {
        return runtime.getArray(value)
    }

    fun getObject(): V8Object? {
        return runtime.getObject(value)
    }

}