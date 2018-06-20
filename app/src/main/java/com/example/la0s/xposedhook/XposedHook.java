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

        if (loadPackageParam.packageName.equals("cn.thecover.www.covermedia")) {    //过滤包名
            Class clasz = loadPackageParam.classLoader.loadClass("cn.thecover.www.covermedia.data.entity.HttpRequestEntity"); //要hook的方法所在的类名

            XposedHelpers.findAndHookMethod(clasz, "getSign",String.class,String.class,String.class, new XC_MethodHook() { //要hook的方法名和参数类型，此处为三个String类型

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    Log.i("hook before param1:", (String) param.args[0]); //打印第一个参数
                    Log.i("hook before param2:", (String) param.args[1]); //打印第一个参数
                    Log.i("hook before param3:", (String) param.args[2]); //打印第一个参数

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    Log.i("hook after result:",param.getResult().toString()); //打印返回值（String类型）
                }
            });
        }
    }
}
