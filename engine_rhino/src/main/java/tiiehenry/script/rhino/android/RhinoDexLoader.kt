package tiiehenry.script.rhino.android

import org.mozilla.javascript.GeneratedClassLoader
import tiiehenry.script.engine.android.ScriptContext
import tiiehenry.script.engine.android.ScriptDexLoader

class RhinoDexLoader(scriptContext: ScriptContext<*>): ScriptDexLoader(scriptContext), GeneratedClassLoader {
    override fun defineClass(p0: String?, p1: ByteArray?): Class<*>? {
        return null;
    }

    override fun linkClass(p0: Class<*>?) {
    }

}