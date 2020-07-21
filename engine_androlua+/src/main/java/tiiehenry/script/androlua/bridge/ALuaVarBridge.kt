package tiiehenry.script.androlua.bridge

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.androlua.lang.ALuaType
import tiiehenry.script.androlua.lang.ALuaVariable
import tiiehenry.script.wrapper.engine.bridge.IVarBridge
import tiiehenry.script.wrapper.engine.lang.IVariable


class ALuaVarBridge(private val engine: ALuaEngine) : IVarBridge<Any, ALuaType> {


    override fun get(name: String): IVariable<Any, ALuaType>? {
        val L = engine.runtime.L
        synchronized(L) {
            L.getGlobal(name)
            return ALuaVariable(L.toJavaObject(-1))
        }

    }

    override fun set(name: String, value: Any?, type: ALuaType?) {
        val L = engine.runtime.L
        synchronized(L) {
            L.pushObjectValue(value)
            L.setGlobal(name)
        }
    }

}