package tiiehenry.script.androlua.framework

import android.content.Context
import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.android.ScriptContext
import tiiehenry.script.engine.framework.ScriptProvider

class ALuaProvider(scriptContext: ScriptContext<ALuaEngine>) : ScriptProvider<ALuaEngine>(scriptContext) {
    val localDir = scriptContext.getContext().filesDir.absolutePath
    val luaMdDir = scriptContext.getContext().getDir("lua", Context.MODE_PRIVATE).absolutePath
    val luaCpath = scriptContext.getContext().applicationInfo.nativeLibraryDir + "/lib?.so" + ";" + soDir + "/lib?.so"
    val luaLpath = "$luaMdDir/?.lua;$luaMdDir/lua/?.lua;$luaMdDir/?/init.lua;"
}