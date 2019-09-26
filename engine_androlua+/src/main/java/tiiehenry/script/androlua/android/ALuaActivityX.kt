package tiiehenry.script.androlua.android

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.android.ScriptActivityX

open class ALuaActivityX : ScriptActivityX<ALuaActivityX, ALuaEngine>(), ALuaContextActivity<ALuaActivityX> {
    override var engine: ALuaEngine = newScriptEngine()

    override fun onEngineInited() {
        super<ALuaContextActivity>.onEngineInited()
    }

    final override fun newScriptEngine(): ALuaEngine {
        return ALuaEngine(this).apply {
        }
    }

    override fun getActivity(): ALuaActivityX {
        return this
    }
}