#include <jni.h>
#include <string>
#include "Looper.h"

extern "C" JNIEXPORT jlong JNICALL
Java_com_baigui_simplehandler_Looper_Init(
        JNIEnv *env,
        jobject /* this */) {
    Looper* nativeMessageQueue = new Looper();
//    NativeMessageQueue* nativeMessageQueue = reinterpret_cast<NativeMessageQueue*>(ptr);
    return reinterpret_cast<jlong>(nativeMessageQueue);
}

extern "C" JNIEXPORT void JNICALL
Java_com_baigui_simplehandler_Looper_Wait(
        JNIEnv *env,
        jobject, /* this */
        jlong ptr,
        jint dealay) {
    Looper* nativeMessageQueue = reinterpret_cast<Looper*>(ptr);
    nativeMessageQueue->wait(dealay);
}


extern "C" JNIEXPORT void JNICALL
Java_com_baigui_simplehandler_Looper_Intrupt(
        JNIEnv *env,
        jobject, /* this */
        jlong ptr) {
    Looper* nativeMessageQueue = reinterpret_cast<Looper*>(ptr);
    nativeMessageQueue->intrupt();
}
//extern "C"
//JNIEXPORT jstring JNICALL
//Java_com_baigui_simplehandler_MainActivity_Init(JNIEnv *env, jobject instance) {
//
//    return env->NewStringUTF(returnValue);
//}