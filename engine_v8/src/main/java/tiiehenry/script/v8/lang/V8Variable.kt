package tiiehenry.script.v8.lang

import com.eclipsesource.v8.*
import tiiehenry.script.wrapper.engine.lang.IVariable

class V8Variable(override val value: Any) : IVariable<Any, V8Type> {
    override fun getType(): V8Type? {
        return when (value) {
            is String -> V8Type.String
            is Int -> V8Type.Integer
            is Double -> V8Type.Double
            is Byte -> V8Type.Byte
            is Boolean -> V8Type.Boolean
            is V8Array -> V8Type.V8Array
            is V8Function -> V8Type.V8Function
            is V8TypedArray -> V8Type.V8TypedArray
            is V8ArrayBuffer -> V8Type.V8ArrayBuffer
            is V8Object -> V8Type.V8Object
            null -> V8Type.Null
            else -> V8Type.Undefined
        }
    }

    override fun getInteger(): Int? {
        if (value is Int)
            return value
        return null
    }

    override fun getBoolean(): Boolean? {
        if (value is Boolean)
            return value
        return null
    }

    override fun getDouble(): Double? {
        if (value is Double)
            return value
        return null
    }

    override fun getString(): String? {
        if (value is String)
            return value
        return null
    }

    fun getArray(): V8Array? {
        if (value is V8Array)
            return value
        return null
    }

    fun getObject(): V8Object? {
        if (value is V8Object)
            return value
        return null
    }


}