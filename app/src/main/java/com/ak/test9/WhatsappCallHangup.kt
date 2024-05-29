package com.ak.test9

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class WhatsappCallHangup: IXposedHookLoadPackage {

    // Whatsapp call hangup
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == "com.whatsapp") {


            XposedHelpers.findAndHookMethod(
                "com.whatsapp.voipcalling.VoipActivityV2",
//                    "com.whatsapp.Conversation",
                lpparam.classLoader,
                "onCreate",
                Bundle::class.java,
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val activity = param.thisObject as Activity
                        try {
                            val delayMillis: Long = 20000

                            Handler(Looper.getMainLooper()).postDelayed({

//                                // Whatsapp voice call btn co-ordinates
//                                val x = 610.0f
//                                val y = 1220.0f
//
//                                // Create a down event
//                                val downTime = System.currentTimeMillis()
//                                val eventTime = System.currentTimeMillis()
//                                val metaState = 0
//                                val motionEventDown = MotionEvent.obtain(
//                                    downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, metaState
//                                )
//
//                                // Create an up event
//                                val motionEventUp = MotionEvent.obtain(
//                                    downTime, eventTime, MotionEvent.ACTION_UP, x, y, metaState
//                                )
//
//                                // Dispatch the events to the activity
//                                activity.dispatchTouchEvent(motionEventDown)
//                                activity.dispatchTouchEvent(motionEventUp)
//
//                                XposedBridge.log("Touch event dispatched at coordinates ($x, $y)")
//
//                                // Recycle the MotionEvent objects to free up resources
//                                motionEventDown.recycle()
//                                motionEventUp.recycle()

                                // Access the root view of the activity
                                val rootView = activity.window
                                    .decorView
                                    .rootView

                                // Find the ImageButton by its ID
                                val imageButton = activity.findViewById<ImageButton>(
                                    activity.resources.getIdentifier(
                                        "footer_end_call_btn",
                                        "id",
                                        activity.packageName
                                    )
                                )

                                if (
//                                    imageButton != null &&
                                    imageButton.visibility == View.VISIBLE) {
                                    imageButton.performClick()

                                    XposedBridge.log("Button clicked programmatically")
                                } else {
                                    XposedBridge.log("ImageButton not found or not visible in VoipActivityV2")
                                }

                            }, delayMillis)


                        } catch (e: Exception) {
                            XposedBridge.log(e)
                        }
                    }


                }
            )
        }
    }

    //    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
//        if (lpparam.packageName == "com.whatsapp") {
//            XposedBridge.log("Target app loaded: ${lpparam.packageName}")
//
//            XposedHelpers.findAndHookMethod(
//                "com.whatsapp.calling.views.VoipCallFooter",
//                lpparam.classLoader,
//                "setEndCallButtonClickListener",
//                View.OnClickListener::class.java,
//                object : XC_MethodHook() {
//                    override fun beforeHookedMethod(param: MethodHookParam) {
//                        XposedBridge.log("setEndCallButtonClickListener hooked")
//
//                        val originalClickListener = param.args[0] as View.OnClickListener
//                        val newClickListener = View.OnClickListener { view ->
//                            // Your custom action
//                            XposedBridge.log("End call button clicked programmatically")
//
//                            // Perform the button click programmatically
//                            view.performClick()
//
//                            // Optionally, call the original click listener
//                            originalClickListener.onClick(view)
//                        }
//                        // Replace the original listener with the new one
//                        param.args[0] = newClickListener
//                    }
//                }
//            )
//        }
//    }
}