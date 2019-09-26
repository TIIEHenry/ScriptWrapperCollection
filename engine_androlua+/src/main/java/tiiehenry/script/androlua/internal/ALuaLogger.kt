package tiiehenry.script.androlua.internal

import tiiehenry.script.androlua.ALuaEngine
import tiiehenry.script.engine.internal.Loggable

open class ALuaLogger(final override val engine: ALuaEngine) :  Loggable<ALuaEngine> {
    override val logTag: String = engine.name


}
