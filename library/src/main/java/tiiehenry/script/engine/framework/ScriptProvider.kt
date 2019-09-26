package tiiehenry.script.engine.framework

import android.content.Context
import tiiehenry.script.engine.android.ScriptContext

open class ScriptProvider<T : ScriptEngine>(val scriptContext: ScriptContext<T>) {

    var odexDir = scriptContext.getContext().getDir("odex", Context.MODE_PRIVATE).absolutePath
    var soDir = scriptContext.getContext().getDir("so", Context.MODE_PRIVATE).absolutePath
    val nativeLibraryDir = scriptContext.getContext().applicationInfo.nativeLibraryDir

}