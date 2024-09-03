package com.ak.test9

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class MicroHook : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName != "com.whatsapp") {
            return
        }

        // Hook the method responsible for microphone input
        XposedHelpers.findAndHookMethod(
            "android.media.AudioRecord",
            lpparam.classLoader,
            "startRecording",
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("before startRecording")
                }

                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("After startRecording")

                }
            })

        // Hook the method responsible for speaker output
        XposedHelpers.findAndHookMethod(
            "android.media.AudioTrack",
            lpparam.classLoader,
            "play",
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("before play")

                }

                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("after play")

                }
            }
        )
    }

}
