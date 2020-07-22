package tiiehenry.script.rhino.internal

import org.mozilla.javascript.Context
import org.mozilla.javascript.ContextFactory
import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.annotations.JSFunction
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
        initStandardObjects(this@RhinoRuntime)

        defineFunctionProperties(arrayOf(
                "print", "println",
                "require", "load"
        ), RhinoRuntime::class.java, ScriptableObject.PERMANENT)
    }

    @JSFunction
    fun require(name: String): Any? {
        return engine.requirer.require(name)
    }

    @JSFunction
    fun load(name: String): Any? {
        return engine.requirer.require(name)
    }

    @JSFunction
    fun print(msg: Any?) {
        engine.printer.print(msg)
    }


    @JSFunction
    fun println(msg: Any?) {
        engine.printer.println(msg)
    }

    override fun getClassName(): String {
        return javaClass.simpleName
    }

}