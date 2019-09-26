package tiiehenry.script.v8.android

import tiiehenry.script.engine.android.ScriptActivity
import tiiehenry.script.v8.V8Engine

open class V8Activity : ScriptActivity<V8Activity, V8Engine>(), V8ContextActivity<V8Activity> {
    override var engine: V8Engine = newScriptEngine()

    final override fun newScriptEngine(): V8Engine {
        return V8Engine(this)
    }

    override fun getActivity(): V8Activity {
        return this
    }
}