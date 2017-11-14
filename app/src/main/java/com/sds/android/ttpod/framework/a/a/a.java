package com.sds.android.ttpod.framework.a.a;

/* CallerUtils */
public class a {
    private static String a(StackTraceElement[] stackTraceElementArr, int i) {
        if (i + 4 >= stackTraceElementArr.length) {
            return "<bottom of call stack>";
        }
        StackTraceElement stackTraceElement = stackTraceElementArr[i + 4];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }

    public static String a() {
        return a(Thread.currentThread().getStackTrace(), 0);
    }
}
