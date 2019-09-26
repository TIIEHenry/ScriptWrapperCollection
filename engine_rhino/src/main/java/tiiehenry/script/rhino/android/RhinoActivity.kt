package tiiehenry.script.rhino.android

import tiiehenry.script.engine.android.ScriptActivity
import tiiehenry.script.rhino.RhinoEngine

open class RhinoActivity : ScriptActivity<RhinoActivity, RhinoEngine>(), RhinoContextActivity<RhinoActivity> {
    override var engine: RhinoEngine = newScriptEngine()

    override fun onEngineInited() {
        super<RhinoContextActivity>.onEngineInited()
    }

    final override fun newScriptEngine(): RhinoEngine {
        return RhinoEngine(this)
    }

    override fun getActivity(): RhinoActivity {
        return this
    }
}