package com.ak.test9

import android.app.Activity
import android.media.AudioTrack
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.lang.reflect.Method
import java.util.*


class Whatsapp : IXposedHookLoadPackage
//    ,IXposedHookInitPackageResources
{

        // Whatsapp call initiate
        override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
            if (lpparam.packageName == "com.whatsapp") {
                XposedHelpers.findAndHookMethod(
                    "com.whatsapp.Conversation",
                    lpparam.classLoader,
                    "onCreate",
                    Bundle::class.java,
                    object : XC_MethodHook() {
                        override fun afterHookedMethod(param: MethodHookParam) {
                        val activity = param.thisObject as Activity
                        try {
                            val delayMillis: Long = 1000

                            Handler(Looper.getMainLooper()).postDelayed({

                                // Whatsapp voice call btn co-ordinates
                                val x = 595.0f
                                val y = 100.0f

                                // Create a down event
                                val downTime = System.currentTimeMillis()
                                val eventTime = System.currentTimeMillis()
                                val metaState = 0
                                val motionEventDown = MotionEvent.obtain(
                                    downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, metaState
                                )

                                // Create an up event
                                val motionEventUp = MotionEvent.obtain(
                                    downTime, eventTime, MotionEvent.ACTION_UP, x, y, metaState
                                )

                                // Dispatch the events to the activity
                                activity.dispatchTouchEvent(motionEventDown)
                                activity.dispatchTouchEvent(motionEventUp)

                                XposedBridge.log("Touch event dispatched at coordinates ($x, $y)")

                                // Recycle the MotionEvent objects to free up resources
                                motionEventDown.recycle()
                                motionEventUp.recycle()

                                // Access the root view of the activity
//                                val rootView = activity.window
//                                    .decorView
//                                    .rootView
//
//                                // Find the ImageButton by its ID
//                                val imageButton = activity.findViewById<ImageButton>(
//                                    activity.resources.getIdentifier(
//                                        "voice_call",
//                                        "id",
//                                        activity.packageName
//                                    )
//                                )
//
//                                if (
////                                    imageButton != null &&
//                                    imageButton.visibility == View.VISIBLE) {
//                                    imageButton.performClick()
//
//                                    XposedBridge.log("Button clicked programmatically")
//                                } else {
//                                    XposedBridge.log("ImageButton not found or not visible in VoipActivityV2")
//                                }

                            }, delayMillis)

                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }
                    }
                )


                val voipClass =
                    XposedHelpers.findClass("com.whatsapp.voipcalling.Voip", lpparam.classLoader)

                var endCallMethod: Method? = null
                for (method in voipClass.getDeclaredMethods()) {
                    if (method.getName().equals("endCall")) {
                        endCallMethod = method
                        break
                    }
                }

                for (method in voipClass.declaredMethods) {
                    if (method.name == "muteCall") {
                        XposedBridge.log("Found muteCall method, hooking it now.")
                        XposedBridge.hookMethod(method, object : XC_MethodHook() {
                            @Throws(Throwable::class)
                            override fun afterHookedMethod(param: MethodHookParam) {
                                XposedBridge.log("After muteCall method")

                                // Assuming endCall takes a boolean and an int as parameters
                                XposedBridge.log("Attempting to call endCall method")
                                val voipInstance = param.thisObject
                                if (voipInstance == null) {
                                    XposedBridge.log("voipInstance is null")
                                    return
                                }

                                // Assuming endCall takes a boolean and an int as parameters

                                // Assuming endCall takes a boolean and an int as parameters
                                val param1 = true // Change these parameters as needed

                                val param2 = 1 // Change these parameters as needed


                                XposedBridge.log("Calling endCall with parameters: $param1, $param2")
                                val result = XposedHelpers.callMethod(
                                    voipInstance,
                                    "endCall",
                                    param1,
                                    param2
                                )
                                XposedBridge.log("endCall result: $result")

                                // Set the result of muteCall to the result of endCall if necessary

                                // Set the result of muteCall to the result of endCall if necessary
                                param.result = result

                                XposedBridge.log("After muteCall success")

                            }
                        })
                    }
                }


            }
        }

}
