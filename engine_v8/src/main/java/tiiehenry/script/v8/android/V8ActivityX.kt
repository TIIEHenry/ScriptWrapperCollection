package tiiehenry.script.v8.android

import tiiehenry.script.engine.android.ScriptActivityX
import tiiehenry.script.v8.V8Engine

open class V8ActivityX : ScriptActivityX<V8ActivityX, V8Engine>(), V8ContextActivity<V8ActivityX> {


    override var engine: V8Engine = newScriptEngine()

    final override fun newScriptEngine(): V8Engine {
        return V8Engine(this)
    }

    override fun getActivity(): V8ActivityX {
        return V8ActivityX()
    }
}