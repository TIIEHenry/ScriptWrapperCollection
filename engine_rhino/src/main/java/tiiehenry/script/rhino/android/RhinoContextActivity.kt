package tiiehenry.script.rhino.android

import android.app.Activity
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.engine.android.ScriptContextActivity
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.LinearLayout.LayoutParams
import android.R.layout
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView


interface RhinoContextActivity<T : Activity> : ScriptContextActivity<T, RhinoEngine> {

    override fun onEngineInited() {


        super.onEngineInited()
    }

}