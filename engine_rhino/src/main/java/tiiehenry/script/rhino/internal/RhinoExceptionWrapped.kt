package tiiehenry.script.rhino.internal

import org.mozilla.javascript.RhinoException
import java.lang.Exception

class RhinoExceptionWrapped(val e: RhinoException) : Exception(
        e.details() + "\n\tat code: " + e.lineSource() +
                /*"\n\tat line: " + e.lineNumber() + "\n\tat file: " + e.sourceName() + */
                "\n" + e.scriptStackTrace, e) {
}