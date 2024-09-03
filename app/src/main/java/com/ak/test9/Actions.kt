package com.ak.test9

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage


class Actions: IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == "com.ak.testx2") {

            XposedHelpers.findAndHookMethod(
                "com.ak.testx2.MainActivity",
                lpparam.classLoader,
                "onCreate",
                Bundle::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Hooked into MainActivity2 onCreate")
                        try {
                            // Access the activity instance
                            val activity = param.thisObject
                            // Find the TextView by its ID
                            val textView =
                                XposedHelpers.getObjectField(activity, "textView") as TextView
                            // Change the color of the TextView text
                            textView.setTextColor(Color.BLUE)

                            val checkBox_Activity = param.thisObject as android.app.Activity

                            // Find the CheckBox field using getObjectField
                            val checkbox =
                                XposedHelpers.getObjectField(checkBox_Activity, "check") as CheckBox

                            // Set the CheckBox to be checked
                            checkbox.isChecked = true

                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }
                }
            )

//            XposedHelpers.findAndHookMethod(
//                "com.whatsapp.calling.views.VoipCallFooter",
//                lpparam.classLoader,
//                "VoipCallFooter",
//                Bundle::class.java,
//                object : XC_MethodHook() {
//                    @Throws(Throwable::class)
//                    override fun afterHookedMethod(param: MethodHookParam) {
//                        try {
//
//                            val activity = param.thisObject as android.app.Activity
////                            val button = activity.findViewById<Button>(
////                                activity.resources.getIdentifier(
////                                    "xposed_btn1",
////                                    "id",
////                                    activity.packageName
////                                )
////                            )
//
////                            if (button != null) {
////                                button.setOnClickListener {
////                                    // Find the button by its ID and set an OnClickListener
////                                    val whatsappActivity = param.thisObject as android.app.Activity
////
////                                    // Find the ImageButton by its ID
////                                    val imageButton = whatsappActivity.findViewById<ImageButton>(
////                                        whatsappActivity.resources.getIdentifier(
////                                            "footer_end_call_btn",
////                                            "id", whatsappActivity.packageName))
////                                    if (imageButton != null) {
////                                        XposedBridge.log("ImageButton found in WhatsApp Conversation, performing click")
////                                        imageButton.performClick()
////                                    } else {
////                                        XposedBridge.log("ImageButton not found in WhatsApp Conversation")
////                                    }
////                                }
////                            } else {
////                                XposedBridge.log("Button not found in SecondActivity")
////                            }
//
//                            try {
//                                // Delay execution by 5 seconds (5000 milliseconds)
//                                val delayMillis: Long = 5000
//
//                                // Use a handler to post a delayed runnable
//                                Handler(Looper.getMainLooper()).postDelayed({
//                                    // Find the ImageButton by its ID (replace "button_id" with the actual button ID)
//                                    val imageButton = activity.findViewById<ImageButton>(
//                                        activity.resources.getIdentifier(
//                                            "footer_end_call_btn", "id", activity.packageName)
//                                    )
//
//                                    if (imageButton != null) {
//                                        XposedBridge.log("ImageButton found in VoipActivityV2, performing click")
//                                        imageButton.performClick()
//                                    } else {
//                                        XposedBridge.log("ImageButton not found in VoipActivityV2")
//                                    }
//                                }, delayMillis)
//
//                            } catch (e: Exception) {
//                                XposedBridge.log(e)
//                            }
//
//                        } catch (e: Exception) {
//                            XposedBridge.log(e)
//                        }
//                    }
//                }
//            )

            XposedHelpers.findAndHookMethod(
                "com.ak.testx2.MainActivity",
                lpparam.classLoader,
                "onCreate",
                Bundle::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Hooked into MainActivity2 onCreate")
                        try {
                            // Access the activity instance
                            val activity = param.thisObject as Activity
                            // Find the TextView by its ID
                            val textView_N = activity.findViewById<TextView>(
                                activity.resources.getIdentifier(
                                    "textView_N", "id", activity.packageName))
                            // Change the color of the TextView text
                            textView_N.setTextColor(Color.BLACK)
                            // Call the native method
                            val nativeText = XposedHelpers.callMethod(activity, "native_Xposed") as String
                            val additionalText = "\n***Xposed Framework***"
                            textView_N.text = "$additionalText"

                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }
                }
            )

        } // Package name equals
//    }// handleLoadPackage
    }

}// Actions