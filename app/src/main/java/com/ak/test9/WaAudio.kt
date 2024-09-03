package com.ak.test9

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


class WaAudio : IXposedHookLoadPackage {

//    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
//        if (lpparam.packageName == "com.whatsapp") {
//
//            try {
//                // Hook System.loadLibrary to intercept loading of native libraries
//                XposedHelpers.findAndHookMethod(
//                    Class.forName("java.lang.System"),
//                    "loadLibrary",
//                    String::class.java,
//                    object : XC_MethodHook() {
//                        override fun afterHookedMethod(param: MethodHookParam) {
//                            val libName = param.args[0] as String
//                            XposedBridge.log("Library loaded: $libName")
//                            if (libName == "whatsapp") {
//                                hookNativeMethods()
//                            }
//                        }
//                    })
//            } catch (e: Exception) {
//                XposedBridge.log(e)
//            }
//
//        }
//    }
//
//    private fun hookNativeMethods() {
//        try {
//            // Use JNI to hook into native methods
//            System.loadLibrary("superpack")
//            val clazz = Class.forName("com.whatsapp.voipcalling.Voip")
//            val method: Method = clazz.getDeclaredMethod("Java_com_whatsapp_voipcalling_Voip_muteCall")
//            XposedBridge.hookMethod(method, object : XC_MethodHook() {
//                override fun beforeHookedMethod(param: MethodHookParam) {
//                    XposedBridge.log("Before native method call")
//                }
//
//                override fun afterHookedMethod(param: MethodHookParam) {
//                    XposedBridge.log("After native method call")
//                }
//            })
//        } catch (e: Exception) {
//            XposedBridge.log(e)
//        }
//    }


    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        // Hook into system libraries
        if (lpparam.packageName == "com.whatsapp") {
//            hookNativeMethods()
//            runFridaScript()  // Whatsapp with root

            XposedHelpers.findAndHookMethod(
                "android.media.AudioRecord",
                lpparam.classLoader,
                "startRecording",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before starting recording")
                        // Add your logic here to handle the microphone input
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After starting recording")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                "android.media.AudioRecord",
                lpparam.classLoader,
                "read",
                ByteArray::class.java,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before reading audio data from microphone")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val audioData = param.args[0] as ByteArray
                        XposedBridge.log(
                            "After reading audio data from microphone: " + Arrays.toString(
                                audioData
                            )
                        )
                        // Modify audio data if needed
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "play",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before playing audio")
                        // Add your logic here to handle the speaker output
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After playing audio")
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "write",
                ByteArray::class.java,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before writing audio data to speaker")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val audioData = param.args[0] as ByteArray
                        XposedBridge.log(
                            "After writing audio data to speaker: " + Arrays.toString(
                                audioData
                            )
                        )
                        // Modify audio data if needed
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                "android.media.AudioManager",
                lpparam.classLoader,
                "setMode",
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before setMode: " + param.args[0])
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After setMode: " + param.args[0])
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "stop",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack stop")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After AudioTrack stop")
                    }
                }
            )

            // Hook AudioTrack constructor

            // Hook AudioTrack constructor
            XposedHelpers.findAndHookConstructor(
                "android.media.AudioTrack",
                lpparam.classLoader,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack constructor")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After AudioTrack constructor")
                    }
                }
            )

            // Hook play method

            // Hook play method
            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "play",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack play")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After AudioTrack play")
                    }
                }
            )

            // Hook stop method

            // Hook stop method
            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "stop",
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack stop")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("After AudioTrack stop")
                    }
                }
            )

            // Hook write method

            // Hook write method
            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "write",
                ByteArray::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack write (byte[])")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val audioData = param.args[0] as ByteArray
                        XposedBridge.log(
                            "After AudioTrack write (byte[]): " + Arrays.toString(
                                audioData
                            )
                        )
                    }
                }
            )

            // Hook an alternative write method

            // Hook an alternative write method
            XposedHelpers.findAndHookMethod(
                "android.media.AudioTrack",
                lpparam.classLoader,
                "write",
                ShortArray::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        XposedBridge.log("Before AudioTrack write (short[])")
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val audioData = param.args[0] as ShortArray
                        XposedBridge.log(
                            "After AudioTrack write (short[]): " + Arrays.toString(
                                audioData
                            )
                        )
                    }
                }
            )


        }

        XposedHelpers.findAndHookMethod(
            "com.whatsapp.voipcalling.VoipActivityV2",
            lpparam.classLoader,
            "onCreate",
            ShortArray::class.java, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    XposedBridge.log("write method called in VoipActivityV2")

                    val activity = param.thisObject
                    // Call the native method
                    // Call the native method
                    XposedHelpers.callMethod(activity, "nativeHook")
                }
            }
        )

    }



    private fun runFridaScript() {
        try {
            // Command to run Frida with the script
            val command = arrayOf<String>(
                "sh", "-c",
                "adb shell frida -U -f com.whatsapp -l /data/data/com.ak.test9/files/testwacall.js"
            )
            val process = Runtime.getRuntime().exec(command)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                Log.d("FridaOutput", line!!)
            }
            process.waitFor()
        } catch (e: Exception) {
            Log.e("FridaError", "Error running Frida script", e)
        }
    }

    private fun hookNativeMethods() {
        // Hook the native methods using Frida
        Runtime.getRuntime().exec("su -c 'frida -U -l testwacall.js -f com.whatsapp'")
    }




}