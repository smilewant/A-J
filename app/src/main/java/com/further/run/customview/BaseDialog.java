package com.further.run.customview;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.further.run.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Hukuan
 * 2018/7/6.
 */
public class BaseDialog extends DialogFragment {
    private SeekBar bar;
    private TextView text;

    private HandlerThread handlerThread ;
    private Handler mThreadHandler;
    private MTHandler mMTHandler = new MTHandler(this);
    private int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_AppCompat_Dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dialog_sample, null);
    }

    @Override
    public void onStart(){
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = MobileUtil.getScreenWidth(getContext()) * 50 / 100;
//            window.setWindowAnimations(animStyle);
//            window.setAttributes(lp);
            window.setLayout(MobileUtil.getScreenWidth(getContext()) * 80 / 100, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        this.setCancelable(false);
//        Window window = getDialog().getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams lp = window.getAttributes();
//            lp.width = MobileUtil.getScreenWidth(getContext()) * 50 / 100;
////            window.setWindowAnimations(animStyle);
////            window.setAttributes(lp);
//            window.setLayout(MobileUtil.getScreenWidth(getContext()) * 50 / 100, ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
        bar = view.findViewById(R.id.seek_bar);
//        bar.setEnabled(false);
        text = view.findViewById(R.id.progress_text);


        handlerThread = new HandlerThread("progress");
        handlerThread.start();

        mThreadHandler = new Handler(handlerThread.getLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                LogUtil.e("I : " + i);

                mMTHandler.sendEmptyMessage(i);
                if (i < 30) {
                    mThreadHandler.sendEmptyMessage(12);
                }
            }
        };
        mThreadHandler.sendEmptyMessage(12);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }



    private static class MTHandler<T> extends Handler {
        private final WeakReference<T> mDialog;
        public MTHandler(T dialogFragment){
            mDialog = new WeakReference<>(dialogFragment);
        }

        @Override
        public void handleMessage(Message msg)
        {
            ((BaseDialog)mDialog.get()).showProgress(msg.what);
        }
    }

    void showProgress(int i){
        bar.setProgress(i * 10 / 3);
        text.setText(i * 10 / 3 + "%");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LogUtil.e("BaseDialog onDestroy" + i);

    }
}
