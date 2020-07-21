package tiiehenry.script.rhino

import tiiehenry.script.rhino.lang.RhinoType
import tiiehenry.script.wrapper.engine.internal.GlobalScriptContext
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.IScriptEngine
import tiiehenry.script.wrapper.IScriptEngineFactory

class RhinoEngineFactory:IScriptEngineFactory<RhinoEngine,Any, RhinoType> {
    override val engineName: String="Rhino"
    override val engineVersion: String="1.7.22"
    override val scriptNames: List<String> = listOf("Rhino","JavaScript","javascript","JS","Js","js")
    override val mimeTypes: List<String> = listOf()
    override val globalScriptContext: IScriptContext= GlobalScriptContext(System.`in`, System.out, System.err)


    override fun newScriptEngine(scriptContext: IScriptContext): RhinoEngine {
        return RhinoEngine(scriptContext)
    }



}