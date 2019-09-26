package tiiehenry.script.v8.internal

import tiiehenry.script.engine.internal.Requirer
import tiiehenry.script.v8.V8Engine

//require 返回保存状态，load 每次都加载
class V8Requirer(val engine: V8Engine):Requirer(engine),Registerable {
    override fun registerRuntime() {
        registerJavaMethod("require",String::class.java)
        registerJavaMethod("load",String::class.java)
    }

    fun registerJavaMethod(name: String,vararg parameterTypes: Class<*>) {
        engine.runtime.registerJavaMethod(this, name, name, parameterTypes)
    }
}