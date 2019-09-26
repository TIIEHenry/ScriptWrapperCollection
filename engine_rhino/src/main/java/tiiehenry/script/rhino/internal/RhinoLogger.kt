package tiiehenry.script.rhino.internal

import tiiehenry.script.engine.internal.Loggable
import tiiehenry.script.rhino.RhinoEngine

open class RhinoLogger(final override val engine: RhinoEngine) :  Loggable<RhinoEngine> {
    override val logTag: String = engine.name


}
