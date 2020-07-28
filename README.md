

## ScriptWrapperCollection

这是基于[ScriptWrapper](https://github.com/TIIEHenry/ScriptWrapper)的已经适配的脚本引擎


## 目录
|  Language  |  Name  |  Repo  | State  |
|  ----  |  ----  | ----  |  ----  |
|  javascript  | rhino |  [Rhino](https://github.com/mozilla/rhino)  |  完全适配  |
|  javascript  | v8 |  [J2V8](https://github.com/eclipsesource/J2V8)  |  基本适配  |
|  lua  | androlua+ |  [AndroLua_pro](https://github.com/nirenr/AndroLua_pro)  |  完全适配  |


## 使用说明
使用上都大致相同

ScriptWrapper提供了ScriptEngineManager，可以通过ScriptEngineManager获取ScriptEngine，这里用Factory

Create Engine
```kotlin
val engine=RhinoEngineFactory.newScriptEngine().apply{
    create()
    //...
}
```

Use requirer

```kotlin
engine.requirer.addFindPath([App.srcDir])
engine.requirer.require("module.js")
```

Use Bridge

```kotlin
val a=engine.varBridge.get("a")?.getString()
val f=engine.funcBridge.get("sum")?.call(1,2)?.getInteger()
```

Use Evaluator

```kotlin
val a=engine.stringEvaluator.eval("var a=1+2\nreturn \"ok\"","scriptName",0)?.getString()
val a2=engine.fileEvaluator.eval(file)?.getString()
val a3=engine.readerEvaluator.eval(FileReader(file))?.value
```

