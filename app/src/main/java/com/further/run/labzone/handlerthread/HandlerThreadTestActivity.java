package com.further.run.labzone.handlerthread;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.further.run.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hukuan
 * 2018/7/17.
 *
 * 1. 是否可以在handlerthread请求okhttp
 * 2.在handlerthread是否需要主动结束
 */
public class HandlerThreadTestActivity extends AppCompatActivity {
    private TextView clickTV;
    private TextView textTV;
    private HandlerThread handlerThread;
    private Handler mThreadHandler;
    private MTHandler mMTHandler = new MTHandler(this);
    private boolean isThreadStop;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread_test);

        clickTV = findViewById(R.id.click);
        textTV = findViewById(R.id.text);
        clickTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThreadStop = false;
//                startProgress();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int j = 0;
                        while (j < 30) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            j++;
                            LogUtil.e("httpClient j " + j);
                        }
                    }
                }).start();
            }
        });
    }

    private void startProgress() {
        handlerThread = new HandlerThread("progress");
        handlerThread.start();

        mThreadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {


                int j = 0;
                while (j < 30) {
                    if (isThreadStop) break;
//                    if (j % 5 == 0) {
//                        request();
//                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    j++;
                    mMTHandler.sendEmptyMessage(j);
                }
            }
        };
//        int j = 0;
//        while (j < 30) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            j++;
//            mThreadHandler.sendEmptyMessage(j);
//        }
        mThreadHandler.sendEmptyMessage(0);
    }

    private void request(){
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().url("http://www.baidu.com").build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("httpClient onFailure");
                handlerThread.quit();
                isThreadStop = true;
//                mThreadHandler.removeMessages(0);
                HandlerThreadTestActivity.this.finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtil.e("httpClient onResponse " + response.toString());
//                showSharePopWindow();
            }
        });


    }

    protected void showSharePopWindow(){
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(MobileUtil.dip2px(160));
        View layout = LayoutInflater.from(this).inflate(R.layout.route_share_popup_window, null);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(layout);
//                popupWindow.setAnimationStyle(R.style.popupAnimation);
        // 需要打开
        popupWindow.showAsDropDown(clickTV, 100, MobileUtil.dip2px(2));
    }

    private static class MTHandler extends Handler {
        private final WeakReference<HandlerThreadTestActivity> mActivity;

        public MTHandler(HandlerThreadTestActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LogUtil.e("httpClient msg.what  " + msg.what + " Thread.currentThread() " + Boolean.toString(mActivity.get().handlerThread.isAlive()));

            mActivity.get().textTV.setText(msg.what + "");
            if (msg.what % 5 == 0) {
                mActivity.get().request();
            }
        }
    }
}
