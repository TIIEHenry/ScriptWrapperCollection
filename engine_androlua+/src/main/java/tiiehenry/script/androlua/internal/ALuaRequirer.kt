package tiiehenry.script.androlua.internal

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.androlua.lang.ALuaType
import tiiehenry.script.wrapper.IScriptContext
import tiiehenry.script.wrapper.engine.internal.IRequirer

class ALuaRequirer(override val engine: ALuaEngine, override val context: IScriptContext =engine.context):IRequirer<Any, ALuaType> {

    override val requireList = ArrayList<String>()
    override val requireMap = HashMap<String, Any?>()
    override val findPathList: MutableList<String> = mutableListOf()

    override fun getFileNameWithExtension(name: String): String {
        if (name.endsWith(".lua"))
            return name
        if (name.endsWith(".aly"))
            return name
        return "$name.lua"
    }
}