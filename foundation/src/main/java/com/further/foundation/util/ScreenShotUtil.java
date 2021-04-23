package com.further.foundation.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContentProviderCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by zion.hu on 5:23 PM, Fri, 4/23/21
 *
 * <p>
 * 描述：截屏工具类
 **/
public class ScreenShotUtil {

    //截取整个可见屏幕内内容
    public static Bitmap DecorShot(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        return activity.getWindow().getDecorView().getDrawingCache();
    }

    //获取单个 view 的截图
    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap bitmapSingleView =  Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapSingleView);
        canvas.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(canvas);
        return bitmapSingleView;
    }

    public static void saveBitmapToLocal(Bitmap bitmap, Context context) {
        File directory = Environment.getExternalStorageDirectory();
        OutputStream outputStream = null;
        String fileName;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = simpleDateFormat.format(date);
        File file = new File(directory.toString(), fileName + ".PNG");
        try {
            outputStream = new FileOutputStream(file);
            boolean save = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri =  Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
        } catch (Exception e) {

        }

    }


}
