package tiiehenry.script.androlua.lang

import tiiehenry.script.wrapper.framework.lang.IVariable

class ALuaVariable(override val value: Any) : IVariable<Any, ALuaType> {
    override fun getType(): ALuaType? {
        return when (value) {
            is String -> ALuaType.String
            is Boolean -> ALuaType.Boolean
            is Int -> ALuaType.Integer
            is Float -> ALuaType.Float
            is Double -> ALuaType.Double
            else -> ALuaType.Object
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
}