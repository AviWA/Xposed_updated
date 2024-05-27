package com.ak.test9

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import kotlinx.coroutines.delay

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

            XposedHelpers.findAndHookMethod(
                "com.ak.testx2.MainActivity2",
                lpparam.classLoader,
                "onCreate",
                Bundle::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Hooked into MainActivity2 Whatsapp method")
                        try {

                            val activity = param.thisObject as android.app.Activity
                            val button = activity.findViewById<Button>(
                                activity.resources.getIdentifier(
                                    "xposed_btn1",
                                    "id",
                                    activity.packageName
                                )
                            )
                            if (button != null) {
                                XposedBridge.log("Button found in SecondActivity, performing click")
                                button.performClick()
                            } else {
                                XposedBridge.log("Button not found in SecondActivity")
                            }

                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }
                }
            )
        }
    }

}