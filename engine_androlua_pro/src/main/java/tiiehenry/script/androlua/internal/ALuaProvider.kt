package tiiehenry.script.androlua.internal

import android.content.Context

class ALuaProvider(private val context: Context) {
    fun getOdexDir(): String {
        return context.getDir("odex", Context.MODE_PRIVATE).absolutePath
    }

    fun getSoDir(): String {
        return context.getDir("so", Context.MODE_PRIVATE).absolutePath
    }

    fun getNativeLibraryDir(): String {
        return context.applicationInfo.nativeLibraryDir
    }

    val localDir = context.filesDir.absolutePath
    val luaMdDir = context.getDir("lua", Context.MODE_PRIVATE).absolutePath
    val luaCpath = context.applicationInfo.nativeLibraryDir + "/lib?.so" + ";" + getSoDir() + "/lib?.so"
    val luaLpath = "$luaMdDir/?.lua;$luaMdDir/lua/?.lua;$luaMdDir/?/init.lua;"
}