package net.lawyee.mobilelib.app;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Method;
/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 标题
 * @Package net.lawyee.mobilelib.app
 * @Description:
 * @author:wuzhu
 * @date: 2017/7/7
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class AndroidHacks {
    private static final String TAG = "Applications";
    private static Object sActivityThread;

    @NonNull
    public static Object getActivityThread() {
        if (sActivityThread == null) {
            synchronized (AndroidHacks.class) {
                if (sActivityThread == null) {
                    sActivityThread = getActivityThreadFromUIThread();
                    if (sActivityThread != null) {
                        return sActivityThread;
                    }

                    if (Looper.getMainLooper() == Looper.myLooper()) {
                        sActivityThread = getActivityThreadFromUIThread();
                    } else {
                        Handler handler = new Handler(Looper.getMainLooper());
                        synchronized (AndroidHacks.class) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    sActivityThread = getActivityThreadFromUIThread();
                                    synchronized (AndroidHacks.class) {
                                        AndroidHacks.class.notify();
                                    }
                                }
                            });
                            try {
                                AndroidHacks.class.wait();
                            } catch (InterruptedException e) {
                                Log.w(TAG, "Waiting notification from UI thread error.", e);
                            }
                        }
                    }
                }
            }
        }
        return sActivityThread;
    }

    private static Object getActivityThreadFromUIThread() {
        Object activityThread = null;
        try {
            Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread");
            method.setAccessible(true);
            activityThread = method.invoke(null);
        } catch (final Exception e) {
            Log.w(TAG, "Failed to get ActivityThread from ActivityThread#currentActivityThread. " +
                    "In some case, this method return null in worker thread.", e);
        }
        return activityThread;
    }
}
