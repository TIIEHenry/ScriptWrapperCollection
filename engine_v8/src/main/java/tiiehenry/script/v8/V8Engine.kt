package tiiehenry.script.v8

import com.eclipsesource.v8.V8
import tiiehenry.script.v8.bridge.V8FuncBridge
import tiiehenry.script.v8.bridge.V8VarBridge
import tiiehenry.script.v8.eval.V8FileEvaluator
import tiiehenry.script.v8.eval.V8ReaderEvaluator
import tiiehenry.script.v8.eval.V8StringEvaluator
import tiiehenry.script.v8.internal.V8Printer
import tiiehenry.script.v8.internal.V8Requirer
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.engine.IScriptEngine
import tiiehenry.script.wrapper.framework.bridge.IFuncBridge
import tiiehenry.script.wrapper.framework.evaluate.IFileEvaluator
import tiiehenry.script.wrapper.framework.evaluate.IStringEvaluator

class V8Engine(override val context: IScriptContext) : IScriptEngine<Any, V8Type> {
    override val funcBridge: IFuncBridge<V8Type> = V8FuncBridge(this)
    override val varBridge = V8VarBridge(this)
    override val stringEvaluator: IStringEvaluator<Any, V8Type> = V8StringEvaluator(this)
    override val fileEvaluator: IFileEvaluator<Any, V8Type> = V8FileEvaluator(this)
    override val readerEvaluator = V8ReaderEvaluator(this)

    lateinit var runtime: V8

    lateinit var printer: V8Printer

    lateinit var requirer: V8Requirer


    override fun create() {
        runtime = V8.createV8Runtime()
        printer = V8Printer(this)
        printer.registerRuntime()

        requirer = V8Requirer(this)
        requirer.registerRuntime()
    }

    override fun pause() {

    }

    override fun resume() {

    }
    override fun destroy() {
        runtime.release(false)
    }

}