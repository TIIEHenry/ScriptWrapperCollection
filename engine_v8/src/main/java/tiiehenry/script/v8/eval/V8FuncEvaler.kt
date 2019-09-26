package tiiehenry.script.v8.eval

import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import tiiehenry.script.engine.eval.FuncEvaler
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.v8.V8Engine

class V8FuncEvaler(engine: V8Engine) : FuncEvaler<V8Engine>(engine) {

    val runtime = engine.runtime
    val onExceptionListener = engine.onExceptionListener

    override fun evalFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Any? {
        var result: Any? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    override fun evalVoidFuncL(name: String, listener: OnExceptionListener, vararg args: Any) {
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                runtime.executeVoidFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
    }

    override fun evalStringFuncL(name: String, listener: OnExceptionListener, vararg args: Any): String? {
        var result: String? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeStringFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    override fun evalIntegerFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Int? {
        var result: Int? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeIntegerFunction(name, array)
            } catch (e: Exception) {
                onExceptionListener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    override fun evalFloatFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Float? {
        return evalDoubleFunc(name, args, listener)?.toFloat()
    }

    override fun evalDoubleFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Double? {
        var result: Double? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeDoubleFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    override fun evalBooleanFuncL(name: String, listener: OnExceptionListener, vararg args: Any): Boolean? {
        var result: Boolean? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeBooleanFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    fun evalArrayFuncL(name: String, listener: OnExceptionListener, vararg args: Any): V8Array? {
        var result: V8Array? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeArrayFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    fun evalObjectFuncL(name: String, listener: OnExceptionListener, vararg args: Any): V8Object? {
        var result: V8Object? = null
        if (!runtime.isReleased) {
            val array = V8Array(runtime)
            args.forEach {
                array.push(it)
            }
            try {
                result = runtime.executeObjectFunction(name, array)
            } catch (e: Exception) {
                listener.onException(e)
            } finally {
                array.close()
            }
        }
        return result
    }

    fun evalJsFuncL(name: String, listener: OnExceptionListener, vararg args: Any?): Any? {
        var result: Any? = null
        if (!runtime.isReleased) {
            try {
                result = runtime.executeJSFunction(name, args)
            } catch (e: Exception) {
                listener.onException(e)
            }
        }
        return result
    }


    fun evalArrayFunc(name: String, vararg args: Any): V8Array? {
        return evalArrayFuncL(name, onExceptionListener, args)
    }

    fun evalObjectFunc(name: String, vararg args: Any): V8Object? {
        return evalObjectFuncL(name, onExceptionListener, args)
    }

    fun evalJsFunc(name: String, vararg args: Any?): Any? {
        return evalJsFuncL(name, onExceptionListener, args)
    }

}