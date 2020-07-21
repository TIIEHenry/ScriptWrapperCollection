package tiiehenry.script.androlua.bridge

import com.luajava.JavaFunction
import com.luajava.LuaException
import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.androlua.lang.ALuaType
import tiiehenry.script.androlua.lang.ALuaFunction
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.bridge.IFuncBridge
import tiiehenry.script.wrapper.framework.lang.IFunction

class ALuaFuncBridge(private val engine: ALuaEngine, override val context: IScriptContext = engine.context) : IFuncBridge<ALuaType> {

    override fun get(name: String): IFunction<ALuaType>? {
        return ALuaFunction(engine.runtime, name)
    }

    override fun set(name: String, function: IFunction<ALuaType>?) {
        val L=engine.runtime.L
//        val i=L.getGlobal(name)
//        L.pushNil()
        val call = object : JavaFunction(L) {
            @Throws(LuaException::class)
            override fun execute(): Int {
                function?.call()
                return 0
            }
        }
        call.register(name)
    }


}