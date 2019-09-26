package tiiehenry.script.v8.bridge

import tiiehenry.script.engine.bridge.FuncBridge
import tiiehenry.script.engine.eval.OnExceptionListener
import tiiehenry.script.v8.V8Engine

class V8FuncBridge (engine: V8Engine):FuncBridge<Any,V8Engine>(engine){
    override fun callFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callVoidFuncL(func: Any, listener: OnExceptionListener, vararg args: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callStringFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callIntegerFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): Int? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callFloatFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): Float? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callDoubleFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): Double? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun callBooleanFuncL(func: Any, listener: OnExceptionListener, vararg args: Any): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}