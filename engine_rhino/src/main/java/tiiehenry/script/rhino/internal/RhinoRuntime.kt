package tiiehenry.script.rhino.internal

import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.annotations.JSFunction
import tiiehenry.script.rhino.RhinoEngine

class RhinoRuntime(val engine: RhinoEngine) : ScriptableObject(), Registerable {
    override fun registerRuntime() {
        engine.runtime.defineFunctionProperties(arrayOf(
                "require", "load",
                "logi", "logd", "logw", "loge", "log",
                "logri", "logrd", "logrw", "logre", "logr",
                "printi", "printd", "printw", "printe", "printf", "print",
                "printri", "printrd", "printrw", "printre", "printrf", "printr"
        ), RhinoRuntime::class.java, PERMANENT)
    }

    @JSFunction
    fun require(name: String): Any? {
        return engine.requirer.require(name)
    }

    @JSFunction
    fun load(name: String): Any? {
        return engine.requirer.load(name)
    }

    override fun getClassName(): String {
        return "RhinoRuntime"
    }


    @JSFunction
    fun printi(a: Any?) {
        engine.printer.printi(a)
    }

    @JSFunction
    fun printd(a: Any?) {
        engine.printer.printd(a)
    }

    @JSFunction
    fun printw(a: Any?) {
        engine.printer.printw(a)
    }

    @JSFunction
    fun printe(a: Any?) {
        engine.printer.printe(a)
    }

    @JSFunction
    fun printf(a: Any?) {
        engine.printer.printf(a)
    }

    @JSFunction
    fun print(a: Any?) {
        engine.printer.print(a)
    }

    @JSFunction
    fun printri(id: Int) {
        engine.printer.printri(id)
    }

    @JSFunction
    fun printrd(id: Int) {
        engine.printer.printrd(id)
    }

    @JSFunction
    fun printrw(id: Int) {
        engine.printer.printrw(id)
    }

    @JSFunction
    fun printre(id: Int) {
        engine.printer.printre(id)
    }

    @JSFunction
    fun printrf(id: Int) {
        engine.printer.printrf(id)
    }

    @JSFunction
    fun printr(id: Int) {
        engine.printer.printr(id)
    }

    @JSFunction
    fun logi(s: Any?) {
        engine.logger.logi(s)
    }

    @JSFunction
    fun logd(s: Any?) {
        engine.logger.logd(s)
    }

    @JSFunction
    fun logw(s: Any?) {
        engine.logger.logw(s)
    }

    @JSFunction
    fun loge(s: Any?) {
        engine.logger.loge(s)
    }

    @JSFunction
    fun log(s: Any?) {
        engine.logger.log(s)
    }

    @JSFunction
    fun logri(id: Int) {
        engine.logger.logri(id)
    }

    @JSFunction
    fun logrd(id: Int) {
        engine.logger.logrd(id)
    }

    @JSFunction
    fun logrw(id: Int) {
        engine.logger.logrw(id)
    }

    @JSFunction
    fun logre(id: Int) {
        engine.logger.logre(id)
    }

    @JSFunction
    fun logr(id: Int) {
        engine.logger.logr(id)
    }
}