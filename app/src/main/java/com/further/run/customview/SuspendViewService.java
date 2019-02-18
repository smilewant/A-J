package com.further.run.customview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.further.run.R;
import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/6/1.
 */
public class SuspendViewService extends Service {
    private WindowManager wm;
    private WindowManager.LayoutParams wmParms;
    private ConstraintLayout layout;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("SuspendViewService onStartCommand");

        return flags;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        create();
    }

    private void create() {
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);wmParms = new WindowManager.LayoutParams();
        wmParms.format = PixelFormat.RGBA_8888;
        wmParms.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParms.gravity = Gravity.TOP | Gravity.START;

        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        wmParms.x = 100;
        wmParms.y = dm.heightPixels;
        //设置悬浮窗口长宽数据
        wmParms.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParms.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParms.alpha = 0.5f;
        wmParms.type = WindowManager.LayoutParams.TYPE_PHONE;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        layout = (ConstraintLayout) inflater.inflate(R.layout.item_main_rv, null);
        wm.addView(layout, wmParms);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                wmParms.x = (int) event.getRawX() - 150;
                //这就是状态栏偏移量用的地方
                wmParms.y = (int) event.getRawY() - 150 ;
                wm.updateViewLayout(layout,wmParms);

                return false;
            }
        });

    }
}
