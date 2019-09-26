package tiiehenry.script.v8.eval

import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.engine.eval.StringEvaler
import tiiehenry.script.v8.V8Engine

class V8StringEvaler(engine: V8Engine) : StringEvaler<V8Engine>(engine) {
    val runtime = engine.runtime
    val onExceptionListener = engine.onExceptionListener


    override fun evalString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Any? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    override fun evalVoidString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener) {
        if (!runtime.isReleased) {
            try {
                runtime.executeVoidScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
    }

    override fun evalStringString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): String? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeStringScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    override fun evalIntegerString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Int? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeIntegerScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    override fun evalFloatString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Float? {
        val r = evalDoubleString(code, scriptName, lineNumber)
        if (r != null)
            return r.toFloat()
        return null
    }

    override fun evalDoubleString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Double? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeDoubleScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    override fun evalBooleanString(code: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): Boolean? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeBooleanScript(code, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    fun evalArrayString(name: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): V8Array? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeArrayScript(name, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }

    fun evalArrayString(name: String, scriptName: String?, lineNumber: Int): V8Array? {
        return evalArrayString(name, scriptName, lineNumber, onExceptionListener)
    }

    fun evalObjectString(name: String, scriptName: String?, lineNumber: Int, listener: OnExceptionListener): V8Object? {
        if (!runtime.isReleased) {
            try {
                return runtime.executeObjectScript(name, scriptName, lineNumber)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return null
    }
    fun evalObjectString(name: String, scriptName: String?, lineNumber: Int): V8Object? {
        return evalObjectString(name, scriptName, lineNumber,onExceptionListener)
    }

}