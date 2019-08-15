package com.further.run.customview;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.further.run.R;
import com.further.foundation.util.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Hukuan
 * 2018/6/1.
 * 需要悬浮窗权限
 */
public class SuspendViewService extends Service {
    private WindowManager wm;
    private WindowManager.LayoutParams wmParms;
    private RelativeLayout layout;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("SuspendViewService onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("SuspendViewService onStartCommand");
        create();
        hiden();
        systembuildInfo();
        getPhoneInfo();
        return flags;
    }

    public void hiden() {

        Class c = Build.class;
//            Build.getRadioVersion()
        Method m = null;
        try {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                LogUtil.e("field : " + field.getName());
            }

            c.getDeclaredMethods();
            m = c.getDeclaredMethod("getFlutterJNI");
            m.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public String systembuildInfo() {
//        return android.os.Build.BRAND;

        String phoneInfo = "Product: " + android.os.Build.PRODUCT;
        phoneInfo += ", CPU_ABI: " + android.os.Build.CPU_ABI;
        phoneInfo += ", TAGS: " + android.os.Build.TAGS;
        phoneInfo += ", TYPE: " + Build.TYPE;
        phoneInfo += ", HOST: " + Build.HOST;
        phoneInfo += ", MODEL: " + Build.MODEL;
        phoneInfo += ", PRODUCT: " + Build.PRODUCT;
        phoneInfo += ", VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ", MODEL: " + android.os.Build.MODEL;
        phoneInfo += ", SDK: " + android.os.Build.VERSION.SDK;
        phoneInfo += ", VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ", DEVICE: " + android.os.Build.DEVICE;
        phoneInfo += ", DISPLAY: " + android.os.Build.DISPLAY;
        phoneInfo += ", BRAND: " + android.os.Build.BRAND;
        phoneInfo += ", BOARD: " + android.os.Build.BOARD;
        phoneInfo += ", FINGERPRINT: " + android.os.Build.FINGERPRINT;
        phoneInfo += ", ID: " + android.os.Build.ID;
        phoneInfo += ", MANUFACTURER: " + android.os.Build.MANUFACTURER;
        phoneInfo += ", USER: " + android.os.Build.USER;
        phoneInfo += ", SERIAL: " + android.os.Build.SERIAL;
        //FINGERPRINT: Meizu/meizu_M5/M5:6.0/MRA58K/1493052752:user/release-keys
        //FINGERPRINT: xiaomi/lavender/lavender:9/PKQ1.180904.001/V10.2.8.0.PFGCNXM:user/release-keys
        //FINGERPRINT: xiaomi/lavender/lavender:9/PKQ1.180904.001/V10.3.1.0.PFGCNXM:user/release-keys

        //SuspendViewService phoneInfo Product: lavender, CPU_ABI: armeabi-v7a, TAGS: release-keys,
        // TYPE: user, HOST: c4-miui-ota-bd05.bj, MODEL: Redmi Note 7, PRODUCT: lavender, VERSION_CODES.BASE: 1,
        // MODEL: Redmi Note 7, SDK: 28, VERSION.RELEASE: 9, DEVICE: lavender, DISPLAY: PKQ1.180904.001,
        // BRAND: xiaomi, BOARD: sdm660, FINGERPRINT: xiaomi/lavender/lavender:9/PKQ1.180904.001/V10.3.1.0.PFGCNXM:user/release-keys, ID: PKQ1.180904.001, MANUFACTURER: Xiaomi, USER: builder, SERIAL: unknown
//        WifiInfo.getFactoryMacAddress();
//mac地址 IP地址
        LogUtil.e("SuspendViewService phoneInfo " + phoneInfo + "\n  phoneInfo hashcode : " + phoneInfo.hashCode());
        return phoneInfo;
        //这些数据组合后hashcode
    }

    public String getPhoneInfo() {



        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        StringBuffer sb = new StringBuffer();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           return sb.toString();

        }
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
        sb.append("\nLine1Number = " + tm.getLine1Number());
        sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());//移动运营商编号
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());//移动运营商名称
        sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
        sb.append("\nSimOperator = " + tm.getSimOperator());

        sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());

        LogUtil.e("SuspendViewService sb.toString() " + sb.toString());
        return  sb.toString();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);

    }

    private void create() {

        LogUtil.e("SuspendViewService create");
        wmParms = new WindowManager.LayoutParams();
        LogUtil.e("SuspendViewService create " + Boolean.toString(wm == null));
//        wmParms.format = PixelFormat.RGBA_8888;
        wmParms.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParms.gravity = Gravity.CENTER;
//
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
        wmParms.x = 100;
        wmParms.y = 300;
//        //设置悬浮窗口长宽数据
        wmParms.width = 100;
        wmParms.height = 100;
////        wmParms.alpha = 0.5f;
        wmParms.type = WindowManager.LayoutParams.TYPE_TOAST;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        layout = (RelativeLayout) inflater.inflate(R.layout.route_share_popup_window, null);
        wm.addView(layout, wmParms);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                wmParms.x = (int) event.getRawX() - 150;
                //这就是状态栏偏移量用的地方
                wmParms.y = (int) event.getRawY() - 150;
                wm.updateViewLayout(layout, wmParms);

                return false;
            }
        });

    }
}
