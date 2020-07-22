package tiiehenry.script.engine.android

import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast

open class ScriptContextHandler(private val scriptContext: ScriptContext<*>) : Handler() {

    private val toastBuilder: StringBuilder = StringBuilder()
    private var toastLastShow: Long = 0

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            ScriptContextConst.Companion.HANDLER.TOAST -> toast(msg.obj.toString())
            ScriptContextConst.Companion.HANDLER.LOG -> log(msg.obj.toString())
        }
    }

    private fun log(text: CharSequence) {
        Log.i(javaClass.simpleName, "log: $text")
    }

    private fun toast(text: CharSequence) {
        val now = System.currentTimeMillis()
        if (now - toastLastShow > 1000) {
            toastBuilder.setLength(0)
            toastBuilder.append(text)
        } else {
            toastBuilder.append("\n")
            toastBuilder.append(text)
        }
        toastLastShow = now
        Toast.makeText(scriptContext.context, toastBuilder.toString(), Toast.LENGTH_SHORT).show()
    }

}