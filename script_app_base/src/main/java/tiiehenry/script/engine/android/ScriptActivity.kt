package tiiehenry.script.engine.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tiiehenry.script.wrapper.IScriptEngine
import tiiehenry.script.wrapper.ScriptEngineManager
import tiiehenry.script.wrapper.engine.internal.GlobalScriptContext
import java.text.SimpleDateFormat
import java.util.*


abstract class ScriptActivity<E : IScriptEngine<*, *>,A:Activity> : AppCompatActivity(), ScriptContextActivity<E,A> {
    override val TAG: String = ScriptActivity::class.toString()
    override lateinit var mainHandler: Handler
    override val toastbuilder: StringBuilder = StringBuilder()
    override var lastShow: Long = 0
    override val logTextBuilder: StringBuilder = StringBuilder()
    override val printTextView: TextView by lazy { TextView(this) }
    override val printDataFormatter: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())//设置日期格式
    override lateinit var engine: E

    abstract val engineName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        mainHandler = ScriptMainHandler(this)
        onCreateBeforeSuper(savedInstanceState)
        super.onCreate(savedInstanceState)
        val factory = ScriptEngineManager.getEngineFactoryByName(engineName)
                ?: return print("can't find factory")
        engine = factory.newScriptEngine(GlobalScriptContext(System.`in`, System.out, System.err)) as E
        onCreateEngine()
        onEngineInited()
        onCreateAfterSuper(savedInstanceState)
    }

    abstract fun onCreateEngine()

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransitionOpen()
    }

    override fun onDestroy() {
        super.onDestroy()
        engine.destroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun getContext(): Context {
        return this
    }

}