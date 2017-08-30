package net.lawyee.mobilelib.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ViewUtil
{
	/**
	 * 获取屏幕宽
	 * @param context
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	/**
	 * 获取屏幕高
	 * @param context
	 */
	public static int getScreenHeight(Context context)
	{
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}
}
