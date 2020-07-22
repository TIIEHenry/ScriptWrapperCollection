package tiiehenry.script.engine.android

import android.app.Activity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import tiiehenry.script.wrapper.engine.IScriptEngine
import java.util.*


interface ScriptContextActivity<E : IScriptEngine<*, *>, A : Activity> : ScriptContext<E> {
    val activity: A
    override val mainHandler: ScriptContextActivityHandler

    fun getThemeID(): Int {
        return android.R.style.Theme_DeviceDefault_Light_DarkActionBar
    }

    val printTextView: TextView


    override fun onEngineInited() {
        super.onEngineInited()
        engine.apply {
            varBridge.set("activity", activity)
            varBridge.set("context", context)
        }

        activity.setTheme(getThemeID())
        printTextView.apply {
            setPadding(10, 10, 10, 10)
            setTextIsSelectable(true)
        }

        val scroll = ScrollView(context).apply {
            isFillViewport = true
            addView(printTextView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }

        activity.setContentView(scroll)

        activity.intent?.let {
            processIntent(it)
        }
    }


    fun overridePendingTransitionOpen() {
        activity.overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)
    }

    fun overridePendingTransitionExit() {
        activity.overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }
}