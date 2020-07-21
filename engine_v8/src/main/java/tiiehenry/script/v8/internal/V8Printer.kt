package tiiehenry.script.v8.internal

import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.v8.lang.V8Variable
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.lang.IFunction
import tiiehenry.script.wrapper.engine.internal.IPrinter
import tiiehenry.script.wrapper.engine.lang.IVariable

class V8Printer(override val engine: V8Engine, override val context: IScriptContext = engine.context) : IPrinter<Any, V8Type> {

    override fun print(msg: Any?) {
        msg?.let {
            context.output.print(V8Variable(it).getString() ?: "null")
        }
    }

    fun registerRuntime() {
        engine.funcBridge.set("print",object :IFunction<V8Type>{
            override fun call(vararg args: Any): IVariable<*, V8Type>? {
                if (args.isNotEmpty()) {
                    print(args.first())
                }else{
                    print(args)
                }
                return null
            }
        })
        engine.funcBridge.set("println",object :IFunction<V8Type>{
            override fun call(vararg args: Any): IVariable<*, V8Type>? {
                if (args.isNotEmpty()) {
                    print(args.first())
                }else{
                    print(args)
                }
                return null
            }
        })
//        registerJavaMethod("print",Any::class.java)
//        registerJavaMethod("println",Any::class.java)
    }

    private fun registerJavaMethod(name: String, vararg parameterTypes: Class<*>) {
        engine.runtime.registerJavaMethod(this, name, name, parameterTypes)
    }
}
