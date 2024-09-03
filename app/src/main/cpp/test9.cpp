#include <jni.h>

#include <dlfcn.h>
#include <android/log.h>

#define LOG_TAG "NativeHook"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

// Original function pointer type
typedef void (*original_function_type)(JNIEnv *, jobject);

// Pointer to the original function
original_function_type original_function;

// Our hooked function
void hooked_function(JNIEnv *env, jobject thiz) {
    LOGI("Hooked function called!");
    // Call the original function if necessary
    // original_function(env, thiz);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ak_test9_MainActivity_nativeHook(JNIEnv *env, jobject thiz) {
    void *handle = dlopen("libunityjni.so", RTLD_NOW);
    LOGI("test9_2","Hooked function called! == Native Hook");

    if (handle) {
        void *symbol = dlsym(handle, "Java_com_whatsapp_voipcalling_Voip_muteCall");
        if (symbol) {
            original_function = (original_function_type) symbol;
            // Replace the original function with our hooked function
            // Use appropriate inline hooking library
            // inline_hook(symbol, (void*)hooked_function);
            LOGI("Native function hooked!");
        } else {
            LOGI("Failed to find target function");
        }
        dlclose(handle);
    } else {
        LOGI("Failed to open target library");
    }
}

//#define LOG_TAG "OpenSLESHook"
//#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
//#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
//
//typedef SLresult (*slCreateEngine_t)(SLObjectItf *, SLuint32, const SLEngineOption *, SLuint32, const SLInterfaceID *, const SLboolean *);
//slCreateEngine_t original_slCreateEngine;
//
//SLresult hooked_slCreateEngine(SLObjectItf *pEngine, SLuint32 numOptions, const SLEngineOption *pEngineOptions, SLuint32 numInterfaces, const SLInterfaceID *pInterfaceIds, const SLboolean *pInterfaceRequired) {
//    LOGI("slCreateEngine called");
//    // Call original function
//    return original_slCreateEngine(pEngine, numOptions, pEngineOptions, numInterfaces, pInterfaceIds, pInterfaceRequired);
//}
//
//extern "C"
//JNIEXPORT void JNICALL
//Java_com_ak_test9_NativeHook_init(JNIEnv *env, jobject /* this */) {
//void *handle = dlopen("libOpenSLES.so", RTLD_LAZY);
//if (!handle) {
//LOGE("Failed to open libOpenSLES.so");
//return;
//}
//
//original_slCreateEngine = (slCreateEngine_t) dlsym(handle, "slCreateEngine");
//if (!original_slCreateEngine) {
//LOGE("Failed to find slCreateEngine");
//dlclose(handle);
//return;
//}
//
//// Replace the function pointer with our hooked function
//if (mprotect((void *) ((uintptr_t) original_slCreateEngine & ~(PAGE_SIZE - 1)), PAGE_SIZE, PROT_READ | PROT_WRITE | PROT_EXEC) == 0) {
//*reinterpret_cast<void **>(&original_slCreateEngine) = reinterpret_cast<void *>(&hooked_slCreateEngine);
//LOGI("slCreateEngine hooked successfully");
//} else {
//LOGE("Failed to change memory protection");
//}
//
//dlclose(handle);
//}
