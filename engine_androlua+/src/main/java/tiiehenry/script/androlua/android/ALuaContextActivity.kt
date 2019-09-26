package tiiehenry.script.androlua.android

import android.app.Activity
import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.android.ScriptContextActivity

interface ALuaContextActivity<T : Activity> : ScriptContextActivity<T, ALuaEngine> {

    override fun onEngineInited() {
        super.onEngineInited()
        engine.apply {
            varBridge.putVar("activity", getActivity())
            varBridge.putVar("context", getContext())
        }
    }
}