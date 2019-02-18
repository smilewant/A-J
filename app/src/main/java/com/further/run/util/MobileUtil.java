package com.further.run.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by Hukuan
 * 2018/5/23.
 */
public class MobileUtil {
    private static int width, height;
    public static int getScreenWidth(Context context) {
        if (width != 0) {
            return width;
        }
        width = getDisplayMetrics(context.getApplicationContext()).widthPixels;
        return width;
    }

    public static int getScreenHeight(Context context) {
        if (height != 0) {
            return height;
        }
        height = getDisplayMetrics(context).heightPixels;
        return height;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static int dip2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }
}
