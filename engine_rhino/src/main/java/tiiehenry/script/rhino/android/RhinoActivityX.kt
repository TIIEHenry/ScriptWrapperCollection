package tiiehenry.script.rhino.android

import tiiehenry.script.engine.android.ScriptActivityX
import tiiehenry.script.rhino.RhinoEngine

open class RhinoActivityX : ScriptActivityX<RhinoActivityX, RhinoEngine>(), RhinoContextActivity<RhinoActivityX> {
    override var engine: RhinoEngine = newScriptEngine()

    override fun onEngineInited() {
        super<RhinoContextActivity>.onEngineInited()
    }

    final override fun newScriptEngine(): RhinoEngine {
        return RhinoEngine(this)
    }

    override fun getActivity(): RhinoActivityX {
        return this
    }
}