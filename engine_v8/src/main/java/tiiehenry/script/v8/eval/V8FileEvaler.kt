package tiiehenry.script.v8.eval

import com.eclipsesource.v8.V8Array
import com.eclipsesource.v8.V8Object
import tiiehenry.script.v8.V8Engine
import tiiehenry.script.engine.eval.FileEvaler
import java.io.File

class V8FileEvaler(engine: V8Engine) : FileEvaler<V8Engine>(engine) {

    fun evalArrayFile(path: String): V8Array? {
        return evalArrayFile(File(path))
    }

    fun evalArrayFile(file: File): V8Array? {
        return engine.stringEvaler.evalArrayString(file.readText(), file.name,0)
    }

    fun evalObjectFile(path: String): V8Object? {
        return evalObjectFile(File(path))
    }

    fun evalObjectFile(file: File): V8Object? {
        return engine.stringEvaler.evalObjectString(file.readText(), file.name,0)
    }
}