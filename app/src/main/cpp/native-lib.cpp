#include <jni.h>
#include <string>
#include <dlfcn.h>
#include <android/log.h>

#define LOG_TAG "OpenSLESHook"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

typedef SLresult (*slCreateEngine_t)(SLObjectItf *, SLuint32, const SLEngineOption *, SLuint32, const SLInterfaceID *, const SLboolean *);
slCreateEngine_t original_slCreateEngine;

SLresult hooked_slCreateEngine(SLObjectItf *pEngine, SLuint32 numOptions, const SLEngineOption *pEngineOptions, SLuint32 numInterfaces, const SLInterfaceID *pInterfaceIds, const SLboolean *pInterfaceRequired) {
    LOGI("slCreateEngine called");
    // Call original function
    return original_slCreateEngine(pEngine, numOptions, pEngineOptions, numInterfaces, pInterfaceIds, pInterfaceRequired);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ak_test9_NativeHook_init(JNIEnv *env, jobject /* this */) {
void *handle = dlopen("libOpenSLES.so", RTLD_LAZY);
if (!handle) {
LOGE("Failed to open libOpenSLES.so");
return;
}

original_slCreateEngine = (slCreateEngine_t) dlsym(handle, "slCreateEngine");
if (!original_slCreateEngine) {
LOGE("Failed to find slCreateEngine");
dlclose(handle);
return;
}

// Replace the function pointer with our hooked function
if (mprotect((void *) ((uintptr_t) original_slCreateEngine & ~(PAGE_SIZE - 1)), PAGE_SIZE, PROT_READ | PROT_WRITE | PROT_EXEC) == 0) {
*reinterpret_cast<void **>(&original_slCreateEngine) = reinterpret_cast<void *>(&hooked_slCreateEngine);
LOGI("slCreateEngine hooked successfully");
} else {
LOGE("Failed to change memory protection");
}

dlclose(handle);
}
