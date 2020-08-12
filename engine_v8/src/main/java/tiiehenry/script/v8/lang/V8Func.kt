package tiiehenry.script.v8.lang

import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Function
import tiiehenry.script.v8.V8Engine
import tiiehenry.script.wrapper.framework.lang.IFunction
import tiiehenry.script.wrapper.framework.lang.IVariable


class V8Func(private val engine: V8Engine, private val func: V8Function) : IFunction<V8Type> {
    override fun call(vararg args: Any?): IVariable<*, V8Type>? {
        val parameters = V8Array(engine.runtime)
        for (arg in args) {
            parameters.push(arg)
        }
        val ret = func.call(engine.runtime, parameters) ?: return null
        return V8Variable(ret)
    }
}