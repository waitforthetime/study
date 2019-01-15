#include <jni.h>
#include <stdio.h>
#include "com_wy_study_calldll_Runner.h"

JNIEXPORT void JNICALL Java_com_wy_study_calldll_Runner_hello(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}
