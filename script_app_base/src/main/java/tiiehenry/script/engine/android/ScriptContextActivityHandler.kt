package tiiehenry.script.engine.android

import android.os.Message
import java.text.SimpleDateFormat
import java.util.*

class ScriptContextActivityHandler(private val scriptContextActivity: ScriptContextActivity<*, *>) : ScriptContextHandler(scriptContextActivity) {
    private val logTextBuilder: StringBuilder = StringBuilder()
    private val printDataFormatter: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())//设置日期格式

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when (msg.what) {
            ScriptContextConst.Companion.HANDLER.PRINT -> print(msg.obj.toString())
            ScriptContextConst.Companion.HANDLER.PRINTLN -> {
                print(msg.obj.toString())
                print("\n")
            }
        }
    }

    private fun print(text: CharSequence) {
        if (text == "\n")
            logTextBuilder.append("\n")
        logTextBuilder.append(printDataFormatter.format(Date()))
        logTextBuilder.append(": ")
        logTextBuilder.append(text)
        scriptContextActivity.printTextView.text = logTextBuilder.toString()
    }

}