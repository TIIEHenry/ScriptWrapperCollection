package tiiehenry.script.engine.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tiiehenry.script.wrapper.engine.IScriptEngine
import tiiehenry.script.wrapper.engine.ScriptEngineManager
import tiiehenry.script.wrapper.framework.internal.GlobalScriptContext


abstract class ScriptActivity<E : IScriptEngine<*, *>,A:Activity> : AppCompatActivity(), ScriptContextActivity<E,A> {
    override val printTextView: TextView by lazy { TextView(this) }
    override lateinit var engine: E
    override lateinit var mainHandler: ScriptContextActivityHandler
    abstract val engineName: String
    override val context: Context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        mainHandler = ScriptContextActivityHandler(this)
        onCreateBeforeSuper(savedInstanceState)
        super.onCreate(savedInstanceState)
        val factory = ScriptEngineManager.getEngineFactoryByName(engineName)
                ?: return print("can't find factory")
        engine = factory.newScriptEngine(GlobalScriptContext(System.`in`,ScriptOutputStream(this), ScriptOutputStream(this))) as E
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
}