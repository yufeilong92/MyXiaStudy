package net.lawyee.mobilelib.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;

public class AppContext {

    @NonNull
    public static Application context() {
        return CURRENT;
    }
    @SuppressLint("StaticFieldLeak")
    private static final Application CURRENT;

    private static AppContext sInstance;

    static {
        try {
            Object activityThread = AndroidHacks.getActivityThread();
            Object app = activityThread.getClass().getMethod("getApplication").invoke(activityThread);
            CURRENT = (Application) app;
        } catch (Throwable e) {
            throw new IllegalStateException("Can not access Application context by magic code, boom!", e);
        }
    }
}
