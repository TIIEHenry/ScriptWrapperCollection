package tiiehenry.script.v8

import tiiehenry.script.v8.lang.V8Type
import tiiehenry.script.wrapper.framework.internal.GlobalScriptContext
import tiiehenry.script.wrapper.engine.IScriptContext
import tiiehenry.script.wrapper.engine.IScriptEngineFactory

class V8EngineFactory: IScriptEngineFactory<V8Engine, Any, V8Type> {
    override val engineName: String="V8"
    override val engineVersion: String="5.0.103"
    override val scriptNames: List<String> = listOf("JavaScript","javascript","JS","Js","js")
    override val mimeTypes: List<String> = listOf()
    override val globalScriptContext: IScriptContext = GlobalScriptContext(System.`in`, System.out, System.err)

    override fun newScriptEngine(scriptContext: IScriptContext): V8Engine {
        return V8Engine(scriptContext)
    }


}