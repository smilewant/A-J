package com.further.run.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.further.run.log.LogUtil;

import static android.os.Build.CPU_ABI;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

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

    public static boolean isARMv7Compatible() {
        try {
            if (SDK_INT >= LOLLIPOP) {
                for (String abi : Build.SUPPORTED_32_BIT_ABIS) {
                    if (abi.equals("armeabi-v7a")) {
                        return true;
                    }
                }
            } else {
                if (CPU_ABI.equals("armeabi-v7a") || CPU_ABI.equals("arm64-v8a")) {
                    return true;
                }
            }
        } catch (Throwable e) {
            LogUtil.e("isARMv7Compatible " + e.getMessage());
        }
        return false;
    }
}
