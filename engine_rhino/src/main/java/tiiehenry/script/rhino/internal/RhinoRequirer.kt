package tiiehenry.script.rhino.internal

import org.mozilla.javascript.ScriptableObject
import org.mozilla.javascript.annotations.JSFunction
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.framework.internal.IRequirer


//require 返回保存状态，load 每次都加载
open class RhinoRequirer(override val engine: RhinoEngine, override val context: IScriptContext = engine.context) :  IRequirer<Any, RhinoType> {

    override val requireList = ArrayList<String>()
    override val requireMap = HashMap<String, Any?>()
    override val findPathList: MutableList<String> = mutableListOf()

    override fun getFileNameWithExtension(name: String): String {
        if (name.endsWith(".js"))
            return name
        return "$name.js"
    }

    fun registerRuntime() {
        engine.runtime.defineFunctionProperties(arrayOf(
                "require", "load"
        ), RhinoRequirer::class.java, ScriptableObject.PERMANENT)
    }

    @JSFunction
    override fun require(name: String): Any? {
        return super.require(name)
    }

    @JSFunction
    override fun load(name: String): Any? {
        return super.load(name)
    }

}