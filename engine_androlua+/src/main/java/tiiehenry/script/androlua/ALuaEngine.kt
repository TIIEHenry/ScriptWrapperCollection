package tiiehenry.script.androlua

import com.luajava.LuaState
import tiiehenry.script.androlua.bridge.ALuaFuncBridge
import tiiehenry.script.androlua.bridge.ALuaVarBridge
import tiiehenry.script.androlua.eval.ALuaFileEvaler
import tiiehenry.script.androlua.eval.ALuaFuncEvaler
import tiiehenry.script.androlua.eval.ALuaStringEvaler
import tiiehenry.script.androlua.framework.ALuaProvider
import tiiehenry.script.androlua.internal.ALuaLogger
import tiiehenry.script.androlua.internal.ALuaPrinter
import tiiehenry.script.androlua.internal.ALuaRuntime
import tiiehenry.script.engine.android.ScriptContext
import tiiehenry.script.engine.framework.ScriptEngine

class ALuaEngine(scriptContext: ScriptContext<ALuaEngine>) : ScriptEngine(scriptContext) {


    override val name: String = "Rhino"


    override lateinit var logger: ALuaLogger
    override lateinit var printer: ALuaPrinter

    override lateinit var funcBridge: ALuaFuncBridge
    override lateinit var varBridge: ALuaVarBridge

    override lateinit var funcEvaler: ALuaFuncEvaler
    override lateinit var stringEvaler: ALuaStringEvaler
    override lateinit var fileEvaler: ALuaFileEvaler


    lateinit var runtime: ALuaRuntime

    var provider: ALuaProvider= ALuaProvider(scriptContext)

    override fun init(globalAlias: String) {
        runtime = ALuaRuntime(this)
        runtime.registerRuntime()


        logger = ALuaLogger(this)
        printer = ALuaPrinter(this)

        funcBridge = ALuaFuncBridge(this)
        varBridge = ALuaVarBridge(this)

        funcEvaler = ALuaFuncEvaler(this)
        stringEvaler = ALuaStringEvaler(this)
        fileEvaler = ALuaFileEvaler(this)
        initVars()

    }

    fun initVars() {
        varBridge.putVar("engine", runtime)
    }

    override fun destory() {
        System.gc()
        runtime.L.gc(LuaState.LUA_GCCOLLECT, 1)
        runtime.L.close()
    }

}