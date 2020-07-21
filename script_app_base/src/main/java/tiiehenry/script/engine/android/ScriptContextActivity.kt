package tiiehenry.script.engine.android

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import tiiehenry.script.wrapper.IScriptEngine
import java.text.SimpleDateFormat
import java.util.*


interface ScriptContextActivity<E: IScriptEngine<*, *>,A:Activity> : ScriptContext<E> {
    fun getActivity(): A

    fun getThemeID(): Int {
        return android.R.style.Theme_DeviceDefault_Light_DarkActionBar
    }

    val printTextView: TextView
    val printDataFormatter : SimpleDateFormat


    override fun onEngineInited() {
        super.onEngineInited()
        engine.apply {
            varBridge.set("activity", getActivity())
            varBridge.set("context", getContext())
        }

        getActivity().setTheme(getThemeID())
        printTextView.apply {
            setPadding(10,10,10,10)
            setTextIsSelectable(true)
        }

        val scroll = ScrollView(getContext()).apply {
            isFillViewport = true
            addView(printTextView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }

        getActivity().setContentView(scroll)

        getActivity().intent?.let {
            processIntent(it)
        }
    }

    override fun print(text: String) {
        super.print(text)
        logTextBuilder.append(printDataFormatter.format(Date()))
        logTextBuilder.append(": ")
        logTextBuilder.append(text)
        logTextBuilder.append("\n")
        printTextView.text = logTextBuilder.toString()
    }


    fun overridePendingTransitionOpen() {
        getActivity().overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)
    }

    fun overridePendingTransitionExit() {
        getActivity().overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }
}