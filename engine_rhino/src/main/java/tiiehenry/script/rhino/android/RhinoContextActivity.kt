package tiiehenry.script.rhino.android

import android.app.Activity
import dalvik.system.DexClassLoader
import org.mozilla.javascript.Context
import org.mozilla.javascript.ContextFactory
import tiiehenry.script.engine.android.ScriptContextActivity
import tiiehenry.script.engine.android.ScriptDexClassLoader
import tiiehenry.script.engine.android.ScriptDexLoader
import tiiehenry.script.rhino.RhinoEngine
import java.io.File


interface RhinoContextActivity<T : Activity> : ScriptContextActivity<T, RhinoEngine> {
//    override fun loadDex(path: String): ScriptDexClassLoader? {
//        return (ContextFactory.getGlobal().applicationClassLoader as ScriptDexLoader).loadDexFile(File(path))
////        return super.loadDex(path).apply {
////            engine.context.applicationClassLoader=this
////        }
//    }

//    override fun loadDexFile(file: File): ScriptDexClassLoader? {
//        return (ContextFactory.getGlobal().applicationClassLoader as RhinoDexLoader).loadDexFile(file)
//    }

    override fun onEngineInited() {


        super.onEngineInited()
    }

}