package tiiehenry.script.v8.internal

import tiiehenry.script.engine.internal.Printable
import tiiehenry.script.v8.V8Engine

class V8Printer(override val engine: V8Engine) : Printable<V8Engine>, Registerable {
    override val logTag: String = engine.name

    override fun registerRuntime() {
        registerJavaMethod("printi", Any::class.java)
        registerJavaMethod("printd", Any::class.java)
        registerJavaMethod("printw", Any::class.java)
        registerJavaMethod("printe", Any::class.java)
        registerJavaMethod("printf", Any::class.java)
        registerJavaMethod("print", Any::class.java)

        registerJavaMethod("printri", Int::class.java)
        registerJavaMethod("printrd", Int::class.java)
        registerJavaMethod("printrw", Int::class.java)
        registerJavaMethod("printre", Int::class.java)
        registerJavaMethod("printrf", Int::class.java)
        registerJavaMethod("printr", Int::class.java)
    }
    fun registerJavaMethod(name: String,vararg parameterTypes: Class<*>) {
        engine.runtime.registerJavaMethod(this, name, name, parameterTypes)
    }
}
