package com.androlua;

import android.content.Context;

public class LuaProvider {
    public Context context;
    public String odexDir;
    public String luaDir;
    public String luaMdDir;
    public String luaCpath;
    public String luaLpath;

    public LuaProvider(Context c){
        this.context=c;
        initLuaEngineWorkSpace();
    }
    private void initLuaEngineWorkSpace() {
        String localDir = context.getFilesDir().getAbsolutePath();
        String libDir =context. getDir("lib", Context.MODE_PRIVATE).getAbsolutePath();
        odexDir = context.getDir("odex", Context.MODE_PRIVATE).getAbsolutePath();
        luaMdDir =context. getDir("lua", Context.MODE_PRIVATE).getAbsolutePath();
        luaCpath = context.getApplicationInfo().nativeLibraryDir + "/lib?.so" + ";" + libDir + "/lib?.so";
        luaLpath = luaMdDir+"/?.lua;"+luaMdDir+"/lua/?.lua;"+luaMdDir+"/?/init.lua;";
    }
}
