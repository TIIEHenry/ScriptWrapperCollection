package tiiehenry.script.rhino

import org.mozilla.javascript.Context
import org.mozilla.javascript.ContextFactory
import tiiehenry.script.rhino.bridge.RhinoFuncBridge
import tiiehenry.script.rhino.bridge.RhinoVarBridge
import tiiehenry.script.rhino.eval.RhinoFileEvaluator
import tiiehenry.script.rhino.eval.RhinoReaderEvaluator
import tiiehenry.script.rhino.eval.RhinoStringEvaluator
import tiiehenry.script.rhino.internal.RhinoInputEvaluateTask
import tiiehenry.script.rhino.internal.RhinoPrinter
import tiiehenry.script.rhino.internal.RhinoRequirer
import tiiehenry.script.rhino.internal.RhinoRuntime
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.IScriptEngine
import tiiehenry.script.wrapper.engine.bridge.IFuncBridge
import tiiehenry.script.wrapper.engine.evaluate.IFileEvaluator
import tiiehenry.script.wrapper.engine.evaluate.IStringEvaluator
import tiiehenry.script.wrapper.engine.internal.InputEvaluateTask
import tiiehenry.script.wrapper.engine.internal.Printable
import tiiehenry.script.wrapper.engine.internal.Requirable
import java.io.BufferedReader
import java.io.InputStreamReader

class RhinoEngine(override val context: IScriptContext) : IScriptEngine<Any, RhinoType>
        , Printable,Requirable {

    override val varBridge = RhinoVarBridge(this)
    override val funcBridge: IFuncBridge<RhinoType> = RhinoFuncBridge(this)

    override val stringEvaluator: IStringEvaluator<Any, RhinoType> = RhinoStringEvaluator(this)
    override val fileEvaluator: IFileEvaluator<Any, RhinoType> = RhinoFileEvaluator(this)
    override val readerEvaluator = RhinoReaderEvaluator(this)

    lateinit var runtime: RhinoRuntime

    override lateinit var printer: RhinoPrinter

    override lateinit var requirer: RhinoRequirer

    override fun create() {
        runtime = RhinoRuntime(this)

        varBridge.setAll(context.bindings)


        printer = RhinoPrinter(this)
        printer.registerRuntime()

        requirer = RhinoRequirer(this)
        requirer.registerRuntime()

        RhinoInputEvaluateTask(context.input,this).start()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun destroy() {
        try {
            Context.exit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {

        fun initGlobalClassloader(classLoader: ClassLoader) {
            try {
                ContextFactory.getGlobal().initApplicationClassLoader(classLoader)
            } catch (e: Exception) {
//            e.printStackTrace()
            }
        }
    }

}