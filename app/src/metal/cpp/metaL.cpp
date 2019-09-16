#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_io_github_ponyatov_pforth_MainActivity_metaL_dump(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "<vm:metaL>";
    return env->NewStringUTF(hello.c_str());
}
