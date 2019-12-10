package com.further.foundation.dialog;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.further.foundation.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Zion
 * 2019/12/9.
 */
public class BaseDialog extends DialogFragment{

    private ImageView animImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Theme_AppCompat_Dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.loading_dialog, null);
    }

    @Override
    public void onStart(){
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
//            WindowManager.LayoutParams lp = window.getAttributes();
//            window.setWindowAnimations(animStyle);
//            window.setAttributes(lp);
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        animImg =  view.findViewById(R.id.anim_img);
        startAnimation();
    }


    public void startAnimation() {
        AnimationDrawable anim = (AnimationDrawable) animImg.getBackground();
        if (!anim.isRunning()) {
            anim.start();
        }
    }

    public void stopAnimation() {
        AnimationDrawable anim = (AnimationDrawable) animImg.getBackground();
        if (anim.isRunning()) {
            anim.stop();
        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();

        stopAnimation();
    }
}
