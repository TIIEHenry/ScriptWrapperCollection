package tiiehenry.script.rhino.lang

import org.mozilla.javascript.RhinoException

class RhinoExceptionWrapper(e: RhinoException, scriptName: String?, lineNumber: Int) : Exception(
        e.details() + "\n\tat script: " + scriptName +
                "\n\tat code: " + e.lineSource() +
                "\n\tat line: " + (if (lineNumber == 0) e.lineNumber() else lineNumber) +
                "\n" + e.scriptStackTrace, e)