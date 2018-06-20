package com.example.la0s.xposedhook;



import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by La0s on 2018/6/20.
 */

public class XposedHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("cn.thecover.www.covermedia")) {
            Class clasz = loadPackageParam.classLoader.loadClass("cn.thecover.www.covermedia.data.entity.HttpRequestEntity");

            XposedHelpers.findAndHookMethod(clasz, "getSign",String.class,String.class,String.class, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    Log.i("hook before param1:", (String) param.args[0]);
                    Log.i("hook before param2:", (String) param.args[1]);
                    Log.i("hook before param3:", (String) param.args[2]);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    Log.i("hook after result:",param.getResult().toString());
                }
            });
        }
    }
}