package tiiehenry.script.androlua.internal

import android.os.StrictMode
import com.androlua.LuaContext
import com.androlua.LuaEnhancer
import com.androlua.LuaGcable
import com.androlua.LuaThread
import com.luajava.JavaFunction
import com.luajava.LuaException
import com.luajava.LuaState
import com.luajava.LuaStateFactory
import tiiehenry.script.androlua.ALuaEngine
import java.io.File
import java.util.*

class ALuaRuntime(val engine: ALuaEngine) : LuaStateFactory(), LuaContext, Registerable {

    val L = newLuaState()

    init {
        LuaEnhancer.dexedPath = engine.provider.odexDir
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        L.apply {
            openLibs()
            pushContext(this@ALuaRuntime)
            getGlobal("luajava")
            pop(1)

            getGlobal("package")
            pushString(engine.provider.luaLpath)
            setField(-2, "path")
            pushString(engine.provider.luaCpath)
            setField(-2, "cpath")
            pop(1)
        }

    }

    override fun registerRuntime() {
        engine.printer.register("print")

        val set = object : JavaFunction(L) {
            @Throws(LuaException::class)
            override fun execute(): Int {
                val thread = L.toJavaObject(2) as LuaThread

                thread.set(L.toString(3), L.toJavaObject(4))
                return 0
            }
        }
        set.register("set")

        val call = object : JavaFunction(L) {
            @Throws(LuaException::class)
            override fun execute(): Int {
                val thread = L.toJavaObject(2) as LuaThread

                val top = L.top
                if (top > 3) {
                    val args = arrayOfNulls<Any>(top - 3)
                    for (i in 4..top) {
                        args[i - 4] = L.toJavaObject(i)
                    }
                    thread.call(L.toString(3), args)
                } else if (top == 3) {
                    thread.call(L.toString(3))
                }

                return 0
            }
        }
        call.register("call")
    }


    override fun call(func: String, vararg args: Any?) {
        engine.funcBridge.callFunc(func,args)
    }

    override fun set(key: String, value: Any?) {
        engine.varBridge.putVar(key, value)
    }

    override fun getLuaLpath(): String {
        return engine.provider.luaLpath
    }

    override fun getLuaCpath(): String {
        return engine.provider.luaCpath
    }

    override fun getLuaState(): LuaState {
        return L
    }

    override fun doFile(path: String, vararg arg: Any?): Any? {
        return engine.fileEvaler.evalFileForArgs(File(path), engine.onExceptionListener, arg)
    }

    override fun sendMsg(msg: String?) {
        engine.printer.print(msg)
    }

    override fun sendError(title: String, msg: Exception) {
        engine.printer.printe(title+":"+msg.message)
    }

    private val gclist = ArrayList<LuaGcable>()
    override fun regGc(obj: LuaGcable) {
        gclist.add(obj)
    }

    fun errorReason(error: Int): String {
        when (error) {
            6 -> return "error error"
            5 -> return "GC error"
            4 -> return "Out of memory"
            3 -> return "Syntax error"
            2 -> return "Runtime error"
            1 -> return "Yield error"
        }
        return "Unknown error $error"
    }

}