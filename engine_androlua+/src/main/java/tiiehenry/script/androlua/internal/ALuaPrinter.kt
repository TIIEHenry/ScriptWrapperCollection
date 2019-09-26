package tiiehenry.script.androlua.internal

import com.luajava.JavaFunction
import com.luajava.LuaException
import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.internal.Printable

class ALuaPrinter(override val engine: ALuaEngine) :JavaFunction(engine.runtime.L), Printable<ALuaEngine> {

    override val logTag: String = engine.name

    private val output = StringBuilder()


    @Throws(LuaException::class)
    override fun execute(): Int {
        if (L.top < 2) {
            print("")
            return 0
        }
        for (i in 2..L.top) {
            val type = L.type(i)
            var v: String? = null
            val stype = L.typeName(type)
            if (stype == "userdata") {
                val obj = L.toJavaObject(i)
                if (obj != null)
                    v = obj.toString()
            } else if (stype == "boolean") {
                v = if (L.toBoolean(i)) "true" else "false"
            } else {
                v = L.toString(i)
            }
            if (v == null)
                v = stype
            output.append("\t")
            output.append(v)
            output.append("\t")
        }
        print(output.toString().substring(1, output.length - 1))
        output.setLength(0)
        return 0
    }

}
