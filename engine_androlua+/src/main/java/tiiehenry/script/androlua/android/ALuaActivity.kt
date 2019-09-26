package tiiehenry.script.androlua.android

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.android.ScriptActivity

open class ALuaActivity : ScriptActivity<ALuaActivity, ALuaEngine>(), ALuaContextActivity<ALuaActivity> {
    override var engine: ALuaEngine = newScriptEngine()

    override fun onEngineInited() {
        super<ALuaContextActivity>.onEngineInited()
    }

    final override fun newScriptEngine(): ALuaEngine {
        return ALuaEngine(this).apply {
        }
    }

    override fun getActivity(): ALuaActivity {
        return this
    }
}