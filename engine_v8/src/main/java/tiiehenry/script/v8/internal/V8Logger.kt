package tiiehenry.script.v8.internal

import tiiehenry.script.engine.internal.Loggable
import tiiehenry.script.v8.V8Engine

open class V8Logger(final override val engine: V8Engine) : Registerable, Loggable<V8Engine> {
    override val logTag: String = engine.name


    override fun registerRuntime() {
        registerJavaMethod("logi", Any::class.java)
        registerJavaMethod("logd", Any::class.java)
        registerJavaMethod("logw", Any::class.java)
        registerJavaMethod("loge", Any::class.java)
        registerJavaMethod("log", Any::class.java)

        registerJavaMethod("logri", Int::class.java)
        registerJavaMethod("logrd", Int::class.java)
        registerJavaMethod("logrw", Int::class.java)
        registerJavaMethod("logre", Int::class.java)
        registerJavaMethod("logr", Int::class.java)
    }

    fun registerJavaMethod(name: String,vararg parameterTypes: Class<*>) {
        engine.runtime.registerJavaMethod(this, name, name, parameterTypes)
    }


}
