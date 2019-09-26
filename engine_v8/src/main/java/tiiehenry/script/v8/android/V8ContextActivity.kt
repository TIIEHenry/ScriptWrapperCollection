package tiiehenry.script.v8.android

import android.app.Activity
import tiiehenry.script.engine.android.ScriptContextActivity
import tiiehenry.script.v8.V8Engine


interface V8ContextActivity<T : Activity> : ScriptContextActivity<T, V8Engine> {

    override fun onEngineInited() {
        super.onEngineInited()
    }
}