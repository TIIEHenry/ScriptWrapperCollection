package tiiehenry.script.v8.lang

import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import tiiehenry.script.wrapper.framework.lang.IVariable

class V8ScriptVariable(private val runtime: V8, override val value: String, private val scriptName: String?, private val lineNumber: Int) : IVariable<Any, V8Type> {
    override fun getType(): V8Type? {
        return V8Type.valueOf(runtime.getType(value))
    }


    fun get(): Any? {
        return runtime.executeScript(value, scriptName, lineNumber)
    }

    fun getVoid() {
        runtime.executeVoidScript(value, scriptName, lineNumber)
    }


    override fun getInteger(): Int? {
        return runtime.executeIntegerScript(value, scriptName, lineNumber)
    }

    override fun getBoolean(): Boolean? {
        return runtime.executeBooleanScript(value, scriptName, lineNumber)
    }

    override fun getDouble(): Double? {
        return runtime.executeDoubleScript(value, scriptName, lineNumber)
    }

    override fun getString(): String? {
        return runtime.executeStringScript(value, scriptName, lineNumber)
    }

    fun getArray(): V8Array? {
        return runtime.executeArrayScript(value, scriptName, lineNumber)
    }

    fun getObject(): V8Object? {
        return runtime.executeObjectScript(value, scriptName, lineNumber)
    }

}