package tiiehenry.script.engine.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.StrictMode
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import tiiehenry.script.wrapper.IScriptEngine
import tiiehenry.script.wrapper.engine.internal.Requirable
import java.io.File
import java.net.URLDecoder

interface ScriptContext<E : IScriptEngine<*, *>> {

    val TAG: String

    val engine: E

    val mainHandler: Handler
    val toastbuilder: StringBuilder
    val logTextBuilder: StringBuilder

    var lastShow: Long

    fun getContext(): Context


    fun onEngineInited() {
//        engine.
//        engine.putVar("context", getContext())

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
        it.getStringExtra("scriptBefore")?.let {
            engine.stringEvaluator.eval(it, "scriptBefore")
        }
    }

    fun processWithExtraAfter(it: Intent) {
        it.getStringExtra("scriptAfter")?.let {
            engine.stringEvaluator.eval(it, "scriptAfter")
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
                val code = getContext().assets.open(s).use {
                    it.bufferedReader().readText()
                }
                engine.stringEvaluator.evalSafely(code)
            }
            path.startsWith("file:") -> {
                val pathed = URLDecoder.decode(path, "utf-8")
                        .replace("file:///", "/").replace("file:/", "/")
                engine.let {
                    if (it is Requirable)
                        it.requirer.require(pathed)
                }
            }
            File(path).isFile -> {
                engine.let {
                    if (it is Requirable)
                        it.requirer.require(path)
                }
            }
        }
    }


    fun toast(id: Int) {
        notifyShowToast(getContext().getString(id))
    }

    fun toast(a: Any) {
        notifyShowToast(a.toString())
    }

    fun toast(text: String) {
        val now = System.currentTimeMillis()
        if (now - lastShow > 1000) {
            toastbuilder.setLength(0)
            toastbuilder.append(text)
        } else {
            toastbuilder.append("\n")
            toastbuilder.append(text)
        }
        lastShow = now
        Toast.makeText(getContext(), toastbuilder.toString(), Toast.LENGTH_SHORT).show()
    }

    fun notifyShowToast(text: String) {
        val m = Message().apply {
            what = ScriptMainHandler.TOAST
            obj = text
        }
        mainHandler.sendMessage(m)
    }

    fun log(text: String) {
        Log.i(TAG, text)
    }

    fun notifyLog(text: String) {
        val m = Message().apply {
            what = ScriptMainHandler.LOG
            obj = text
        }
        mainHandler.sendMessage(m)
    }

    fun print(text: String) {
        Log.i(TAG, text)
        toast(text)
    }

    fun notifyPrint(text: String) {
        val m = Message().apply {
            what = ScriptMainHandler.PRINT
            obj = text
        }
        mainHandler.sendMessage(m)
    }

//    fun getString(id: Int): String


    fun getWidth(): Int {
        val wm = getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    fun getHeight(): Int {
        val wm = getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
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