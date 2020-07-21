package tiiehenry.script.v8.internal

import tiiehenry.script.v8.V8Engine
import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.internal.IRequirer
import tiiehenry.script.wrapper.framework.lang.IFunction
import tiiehenry.script.wrapper.framework.lang.IVariable

//require 返回保存状态，load 每次都加载
class V8Requirer(override val engine: V8Engine, override val context: IScriptContext = engine.context) : IRequirer<Any, V8Type> {

    override val requireList = ArrayList<String>()
    override val requireMap = HashMap<String, Any?>()
    override val findPathList: MutableList<String> = mutableListOf()

    override fun getFileNameWithExtension(name: String): String {
        if (name.endsWith(".js"))
            return name
        return "$name.js"
    }

    fun registerRuntime() {
        engine.funcBridge.set("require", object : IFunction<V8Type> {
            override fun call(vararg args: Any): IVariable<*, V8Type>? {
                if (args.isNotEmpty()) {
                    require(args.first().toString())
                } else {
                    require(args.toString())
                }
                return null
            }
        })
        engine.funcBridge.set("load", object : IFunction<V8Type> {
            override fun call(vararg args: Any): IVariable<*, V8Type>? {
                if (args.isNotEmpty()) {
                    load(args.first().toString())
                } else {
                    load(args.toString())
                }
                return null
            }
        })
//        registerJavaMethod("require", String::class.java)
//        registerJavaMethod("load", String::class.java)
    }

    fun registerJavaMethod(name: String, vararg parameterTypes: Class<*>) {
        engine.runtime.registerJavaMethod(this, name, name, parameterTypes)
    }
}