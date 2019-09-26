package tiiehenry.script.v8

import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Value
import tiiehenry.script.engine.android.ScriptContext
import tiiehenry.script.engine.framework.ScriptEngine
import tiiehenry.script.v8.bridge.V8FuncBridge
import tiiehenry.script.v8.bridge.V8VarBridge
import tiiehenry.script.v8.eval.V8FileEvaler
import tiiehenry.script.v8.eval.V8FuncEvaler
import tiiehenry.script.v8.eval.V8StringEvaler
import tiiehenry.script.v8.internal.V8Logger
import tiiehenry.script.v8.internal.V8Printer
import tiiehenry.script.v8.internal.V8Requirer

class V8Engine(scriptContext: ScriptContext<V8Engine>) : ScriptEngine(scriptContext) {

    override val name: String = "V8"

    lateinit var runtime: V8


    override lateinit var logger: V8Logger
    override lateinit var printer: V8Printer

    override lateinit var funcBridge: V8FuncBridge
    override lateinit var varBridge: V8VarBridge

    override lateinit var funcEvaler: V8FuncEvaler
    override lateinit var stringEvaler: V8StringEvaler
    override lateinit var fileEvaler: V8FileEvaler

    override lateinit var requirer: V8Requirer

    override fun init(globalAlias: String) {
        runtime = V8.createV8Runtime(globalAlias)
        logger = V8Logger(this)
        printer = V8Printer(this)

        funcBridge = V8FuncBridge(this)
        varBridge = V8VarBridge(this)

        requirer = V8Requirer(this)
        funcEvaler = V8FuncEvaler(this)
        stringEvaler = V8StringEvaler(this)
        fileEvaler = V8FileEvaler(this)
        logger.registerRuntime()
        printer.registerRuntime()
    }


    override fun destory() {
        runtime.release(false)
    }


    fun checkType(o: Any?): Any? {
        return when {
            o == null -> null
            o is Int -> o as Int
            o is String -> o as String
            o is Long -> o as Long
            o is Float -> o as Float
            o is Double -> o as Double
            o is Boolean -> o as Boolean
            o is V8Value -> o as V8Value

            else -> {
                null
//                val  scriptName,lineNumber = V8Object(runtime)
            }
        }
    }
}