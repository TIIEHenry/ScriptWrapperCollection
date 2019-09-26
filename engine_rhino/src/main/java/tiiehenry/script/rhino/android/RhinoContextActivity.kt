package tiiehenry.script.rhino.android

import android.app.Activity
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.engine.android.ScriptContextActivity

interface RhinoContextActivity<T : Activity> : ScriptContextActivity<T, RhinoEngine> {

    override fun onEngineInited() {
        super.onEngineInited()
        engine.apply {
            varBridge.putVar("activity", getActivity())
            varBridge.putVar("context", getContext())
        }
    }
}