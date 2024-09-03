package com.ak.test9

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage

class WhatsappCall : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
//        if (lpparam.packageName == "com.whatsapp") {
//            XposedHelpers.findAndHookMethod(
//                "com.whatsapp.voipcalling.Voip",
//                lpparam.classLoader,
//                "Java_com_whatsapp_voipcalling_Voip_muteCall",
//                Bundle::class.java,
//                object : XC_MethodHook() {
//
//                    override fun beforeHookedMethod(param: MethodHookParam?) {
//                        XposedBridge.log("Native Method Not Hooked Successfully")
//                    }
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("Native Method Hooked Successfully")
//                    }
//                }
//            )
//        }

//        if(lpparam.packageName == "com.ak.testx2"){
//            XposedHelpers.findAndHookMethod(
//                "com.ak.testx2.MainActivity",
//                lpparam.classLoader,
//                "onCreate",
//                Bundle::class.java,
//                object : XC_MethodHook() {
//                    @Throws(Throwable::class)
//
//                    override fun beforeHookedMethod(param: MethodHookParam?) {
//                        XposedBridge.log("Hooked into native_Xposed")
//                        try {
//                            // Access the activity instance
//                            val activity = param!!.thisObject as Activity
//                            // Find the TextView by its ID
//                            val textView_N = activity.findViewById<TextView>(
//                                activity.resources.getIdentifier(
//                                    "textView_N", "id", activity.packageName))
//                            // Change the color of the TextView text
//                            textView_N.setTextColor(Color.BLACK)
//                            // Call the native method
//                            val nativeText = XposedHelpers.callMethod(activity, "native_Xposed") as String
//                            val additionalText = "\n***Xposed Framework***"
//                            textView_N.text = "$additionalText"
//
//                        } catch (e: Exception) {
//                            XposedBridge.log(e)
//                        }
//                    }
//
//                }
//            )
//        }
    }

}