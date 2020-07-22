package tiiehenry.script.engine.android

import tiiehenry.script.wrapper.framework.internal.OutputPrintStream
import java.util.*

class ScriptOutputStream(private val activity: ScriptContextActivity<*, *>) : OutputPrintStream(System.out, true) {
    override val formatter: Formatter = Formatter(this)

    override fun onPrint(cs: CharSequence) {
            activity.print(cs)
    }

    override fun onNewLine() {
        onPrint("\n")
    }

}