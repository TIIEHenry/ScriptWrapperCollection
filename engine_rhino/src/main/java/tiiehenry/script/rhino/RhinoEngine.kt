package tiiehenry.script.rhino

import org.mozilla.javascript.Context
import tiiehenry.script.engine.android.ScriptContext
import tiiehenry.script.engine.framework.ScriptEngine
import tiiehenry.script.rhino.android.RhinoDexLoader
import tiiehenry.script.rhino.bridge.RhinoFuncBridge
import tiiehenry.script.rhino.bridge.RhinoVarBridge
import tiiehenry.script.rhino.eval.RhinoFileEvaler
import tiiehenry.script.rhino.eval.RhinoFuncEvaler
import tiiehenry.script.rhino.eval.RhinoStringEvaler
import tiiehenry.script.rhino.internal.RhinoLogger
import tiiehenry.script.rhino.internal.RhinoPrinter
import tiiehenry.script.rhino.internal.RhinoRequirer
import tiiehenry.script.rhino.internal.RhinoRuntime
import java.io.File

class RhinoEngine(scriptContext: ScriptContext<RhinoEngine>) : ScriptEngine(scriptContext) {


    override val name: String = "Rhino"

    lateinit var context: Context

    override lateinit var logger: RhinoLogger
    override lateinit var printer: RhinoPrinter

    override lateinit var funcBridge: RhinoFuncBridge
    override lateinit var varBridge: RhinoVarBridge

    override lateinit var funcEvaler: RhinoFuncEvaler
    override lateinit var stringEvaler: RhinoStringEvaler
    override lateinit var fileEvaler: RhinoFileEvaler

    override lateinit var requirer: RhinoRequirer

    lateinit var runtime: RhinoRuntime

    override lateinit var dexLoader: RhinoDexLoader

    override fun init(globalAlias: String) {
        runtime = RhinoRuntime(this)

        dexLoader = RhinoDexLoader(scriptContext)

        context = Context.enter().apply {
            optimizationLevel = -1//dexclassloader needed
            languageVersion = Context.VERSION_ES6
            applicationClassLoader = dexLoader

            instructionObserverThreshold = 10000
        }
        context.initStandardObjects(runtime)
        runtime.registerRuntime()

        logger = RhinoLogger(this)
        printer = RhinoPrinter(this)

        funcBridge = RhinoFuncBridge(this)
        varBridge = RhinoVarBridge(this)

        requirer = RhinoRequirer(this)
        funcEvaler = RhinoFuncEvaler(this)
        stringEvaler = RhinoStringEvaler(this)
        fileEvaler = RhinoFileEvaler(this)
        initVars()
    }

    fun initVars() {
        varBridge.putVar("activity", scriptContext.getContext())
    }

    override fun destory() {
        Context.exit()
    }

}