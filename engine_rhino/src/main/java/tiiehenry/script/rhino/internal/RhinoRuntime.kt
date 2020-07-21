package tiiehenry.script.rhino.internal

import org.mozilla.javascript.Context
import org.mozilla.javascript.ContextFactory
import org.mozilla.javascript.ScriptableObject
import tiiehenry.script.rhino.RhinoEngine

class RhinoRuntime(val engine: RhinoEngine) : ScriptableObject() {
    val rhinoContext: Context = Context.enter().apply {
        optimizationLevel = -1//dex classloader needed
        languageVersion = Context.VERSION_ES6
        try {
            applicationClassLoader = ContextFactory.getGlobal().applicationClassLoader
//                applicationClassLoader = Thread.currentThread().contextClassLoader
        } catch (e: Exception) {
        }
        instructionObserverThreshold = 10000
    }

    init {

        rhinoContext.initStandardObjects(this)
    }

    override fun getClassName(): String {
        return "RhinoRuntime"
    }

}