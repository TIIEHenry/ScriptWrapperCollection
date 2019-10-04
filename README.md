# ScriptEngine
Adapted script engines for [ScriptEngineCore](https://github.com/TIIEHenry/ScriptEngineCore)


这是基于[ScriptEngineCore](https://github.com/TIIEHenry/ScriptEngineCore)的已经适配的脚本引擎


## Catelog(目录)
|  Language  |  Name  |  Repo  | State  |
|  ----  |  ----  | ----  |  ----  |
|  javascript  | rhino |  [Rhino](https://github.com/mozilla/rhino)  |  Adapted  |
|  javascript  | v8 |  [J2V8](https://github.com/eclipsesource/J2V8)  |  Adapted  |
|  lua  | androlua+ |  [AndroLua_pro](https://github.com/nirenr/AndroLua_pro)  |  Adapted  |


## Usage(使用说明)
They're all pretty much the same(使用上都大致相同)

Create Script Runtime
```kotlin
RhinoEngine([ScriptContext<RhinoEngine>]).apply{
    stringEvaler.xxx()
    varBridge.putVar("activity", scriptContext.getContext())
    //....
}
```


Use Requirer:
```kotlin
        Requirer.addFindPath(App.srcDir)
        engine.requirer.require("main")
```
