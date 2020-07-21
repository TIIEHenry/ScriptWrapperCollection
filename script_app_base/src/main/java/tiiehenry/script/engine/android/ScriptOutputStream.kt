package tiiehenry.script.engine.android

import android.widget.TextView
import tiiehenry.script.wrapper.framework.internal.OutputPrintStream
import java.util.*

class ScriptOutputStream(private val activity: ScriptActivity<*, *>, private val textView: TextView) : OutputPrintStream(System.out, true) {
    override val formatter: Formatter = Formatter(this)

    override fun onPrint(cs: CharSequence) {
        activity.runOnUiThread {
            textView.editableText?.append(cs)
        }
    }

    override fun onNewLine() {
        activity.runOnUiThread {
            textView.editableText?.append("\n")
        }
    }

}