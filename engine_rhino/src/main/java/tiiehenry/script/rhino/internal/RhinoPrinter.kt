package tiiehenry.script.rhino.internal

import tiiehenry.script.engine.internal.Printable
import tiiehenry.script.rhino.RhinoEngine

class RhinoPrinter(override val engine: RhinoEngine) : Printable<RhinoEngine> {
    override val logTag: String = engine.name



}
