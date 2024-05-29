package com.ak.test9

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LayoutInflated
import de.robv.android.xposed.callbacks.XC_LoadPackage

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

                            }, delayMillis)

                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }
                    }
                )
            }
        }

}
