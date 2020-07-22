package tiiehenry.script.engine.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.os.StrictMode
import android.util.DisplayMetrics
import android.view.WindowManager
import tiiehenry.script.wrapper.engine.IScriptEngine
import tiiehenry.script.wrapper.framework.internal.Requirable
import java.io.File
import java.io.Reader
import java.net.URLDecoder

interface ScriptContext<E : IScriptEngine<*, *>> {
    val context: Context
    val engine: E

    val mainHandler: ScriptContextHandler


    fun onEngineInited() {
        engine.varBridge.set("context", context)

    }

    //null engine
    fun onCreateBeforeSuper(savedInstanceState: Bundle?) {
        resetPolicy()
    }

    //engine inited but not eval
    fun onCreateAfterSuper(savedInstanceState: Bundle?) {

    }


    fun processIntent(it: Intent) {
        processWithExtraBefore(it)
        when {
            !it.dataString.isNullOrEmpty() ->
                processWithDataString(it.dataString!!)
            it.data != null ->
                processWithData(it.data!!)
        }
        processWithExtraAfter(it)
    }

    fun processWithExtraBefore(it: Intent) {
        it.getStringExtra(ScriptContextConst.Companion.INTENT.SCRIPT_BEFORE_DATA)?.let {
//            Log.e(javaClass.simpleName, "processWithExtraBefore: " + it)
            eval(it,ScriptContextConst.Companion.INTENT.SCRIPT_BEFORE_DATA)
        }
    }

    fun processWithExtraAfter(it: Intent) {
        it.getStringExtra(ScriptContextConst.Companion.INTENT.SCRIPT_AFTER_DATA)?.let {
            eval(it, ScriptContextConst.Companion.INTENT.SCRIPT_AFTER_DATA)
        }
    }

    fun eval(input: String, scriptName: String) {
        try {
            val v = engine.stringEvaluator.eval(input, scriptName)
            println(v?.value.toString())
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }

    fun eval(input: Reader, scriptName: String) {
        try {
            val v = engine.readerEvaluator.eval(input)
            println(v?.value.toString())
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }

    fun eval(input: File, scriptName: String) {
        try {
            val v = engine.fileEvaluator.eval(input)
            println(v?.value.toString())
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }

    fun processWithDataString(dataString: String) {
        processWithPath(dataString)
    }

    fun processWithData(data: Uri) {
        val path = data.path
        path?.let { processWithPath(it) }
    }

    fun processWithPath(path: String) {
        when {
            path.startsWith("file:///android_asset/") -> {
                val s = path.replace("file:///android_asset/", "")
                context.assets.open(s).use {
                    eval(it.bufferedReader(), ScriptContextConst.Companion.INTENT.SCRIPT_AFTER_DATA)
                    engine.readerEvaluator.evalSafely(it.bufferedReader())
                }
            }
            path.startsWith("file:") -> {
                val path2 = URLDecoder.decode(path, "utf-8")
                        .replace("file:///", "/").replace("file:/", "/")

                require(path2)
            }
            File(path).isFile -> {
                require(path)
            }
        }
    }

    fun require(path: String) {
        engine.let {
            if (it is Requirable) {
                try {
                    it.requirer.require(path)
                } catch (e: Exception) {
                    println(e.message.toString())
                }
            }
        }
    }

    fun toast(id: Int) {
        toast(context.getString(id))
    }

    fun toast(a: Any) {
        toast(a.toString())
    }

    fun toast(message: CharSequence) {
        sendMessage(ScriptContextConst.Companion.HANDLER.TOAST, message)
    }

    fun log(message: CharSequence) {
        sendMessage(ScriptContextConst.Companion.HANDLER.LOG, message)
    }

    fun print(message: CharSequence) {
        sendMessage(ScriptContextConst.Companion.HANDLER.PRINT, message)
    }

    fun println(message: CharSequence) {
        sendMessage(ScriptContextConst.Companion.HANDLER.PRINTLN, message)
    }

    fun sendMessage(what: Int, message: CharSequence) {
        Message().let {
            it.what = what
            it.obj = message
            mainHandler.sendMessage(it)
        }
    }

    fun getWidth(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    fun getHeight(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    private fun resetPolicy() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())//绕过provider
    }
}