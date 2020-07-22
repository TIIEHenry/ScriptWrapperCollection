package tiiehenry.script.rhino.lang

import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import org.mozilla.javascript.Scriptable
import org.mozilla.javascript.ScriptableObject
import tiiehenry.script.wrapper.framework.lang.IVariable

class RhinoVariable(override val value: Any) : IVariable<Any, RhinoType> {
    override fun getType(): RhinoType? {
        return when (value) {
             Scriptable.NOT_FOUND->RhinoType.Undefined
            is String -> RhinoType.String
            is Boolean -> RhinoType.Boolean
            is Int -> RhinoType.Integer
            is Float -> RhinoType.Float
            is Double -> RhinoType.Double
            is ScriptableObject -> {
                if ((value as? ScriptableObject)?.typeOf == "object") RhinoType.Object else RhinoType.Undefined
            }
            else -> null
        }
    }

    override fun getString(): String? {
        if (value == Scriptable.NOT_FOUND)
            return null
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
        return Context.toString(value)
    }

    override fun getBoolean(): Boolean? {
        if (value == Scriptable.NOT_FOUND)
            return null
        if (value is Boolean)
            return value
        return Context.toBoolean(value)
    }

    override fun getInteger(): Int? {
        if (value == Scriptable.NOT_FOUND)
            return null
        if (value is Int)
            return value
        return getDouble()?.toInt()
    }

    override fun getFloat(): Float? {
        if (value == Scriptable.NOT_FOUND)
            return null
        if (value is Float)
            return value
        return getDouble()?.toFloat()
    }

    override fun getDouble(): Double? {
        if (value == Scriptable.NOT_FOUND)
            return null
        if (value is Double)
            return value
        return Context.toNumber(value)
    }

    fun getFunction(): Function? {
        if (value == Scriptable.NOT_FOUND)
            return null
        if (value is Function) {
            return value
        }
        return null
    }
}