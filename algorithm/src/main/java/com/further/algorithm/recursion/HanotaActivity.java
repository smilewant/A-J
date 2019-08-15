package com.further.algorithm.recursion;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.further.algorithm.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by Zion
 * 2019/3/26.
 */
public class HanotaActivity extends AppCompatActivity {
    LinearLayout layout1, layout2, layout3;
    Stack begin, middle, end;
    IHandler handler = new IHandler(this);

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanota);
        layout1 = findViewById(R.id.linerlayout_1);
        layout2 = findViewById(R.id.linerlayout_2);
        layout3 = findViewById(R.id.linerlayout_3);
        begin = new Stack();
        middle = new Stack();
        end = new Stack();

        final String[] strs = {"A", "B", "C", "D", "E"};
        for (String s : strs) {
            begin.push(s);
            addText(s, layout1);
            addText("", layout2);
            addText("", layout3);
        }

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        han(strs.length, begin, middle, end, layout1, layout2, layout3);
                    }
                }).start();

                handler.sendEmptyMessage(0);
            }
        });


    }

    public void han(int n, Stack begin, Stack middle, Stack end, LinearLayout layoutBegin, LinearLayout layoutMiddle, LinearLayout layoutEnd) {

        if (n == 1) {
            move(begin, end, layoutBegin, layoutEnd);
        } else {
            han(n - 1, begin, end, middle, layoutBegin, layoutEnd, layoutMiddle);
            move(begin, end, layoutBegin, layoutEnd);
            han(n - 1, middle, begin, end, layoutMiddle, layoutBegin, layoutEnd);
        }


    }

    private void move(Stack begin1, Stack end1, LinearLayout layoutBegin, LinearLayout layoutEnd) {
        String temp = (String) begin1.pop();
        end1.push(temp);
//        layoutBegin.removeView(layoutBegin.getChildAt(layoutBegin.getChildCount() - 1));
//        addText(temp, layoutEnd);

//        ((TextView)layoutBegin.getChildAt(begin.size())).setText("");
//        ((TextView)layoutEnd.getChildAt(end.size() - 1)).setText(temp);
        LogUtil.e("Stack begin : " + begin.toString());
        LogUtil.e("Stack middle : " + middle.toString());
        LogUtil.e("Stack end : " + end.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addText(String s, LinearLayout layout) {
        TextView textView = new TextView(this);
        textView.setText(s);
        textView.setTextColor(ContextCompat.getColor(this, R.color.color_333333));
        textView.setTextSize(16);
        textView.setPadding(MobileUtil.dip2px(10), MobileUtil.dip2px(10), MobileUtil.dip2px(10), MobileUtil.dip2px(10));
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.color_dddddd));
        layout.addView(textView);
    }

    static class IHandler extends Handler {
        private final WeakReference<HanotaActivity> mActivity;

        private IHandler(HanotaActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() != null) {
                mActivity.get().fresh();
            }
        }
    }

    private void fresh() {
//        for(int i = 0; i < 5; i++){
//            ((TextView)layout1.getChildAt(i)).setText("");
//            ((TextView)layout2.getChildAt(i)).setText("");
//            ((TextView)layout3.getChildAt(i)).setText("");
//        }
//
//        String[] temps = begin.toString().split(",");
//        for(int i = temps.length - 1; i > -1; i--){
//            ((TextView)layout1.getChildAt(i)).setText(temps[temps.length- 1 - i]);
//        }
//
//        String[] tempm = middle.toString().split(",");
//        for(int i = tempm.length - 1; i > -1; i--){
//            ((TextView)layout2.getChildAt(i)).setText(tempm[tempm.length- 1 -i]);
//        }
//
//        String[] tempe = end.toString().split(",");
//        for(int i = tempe.length - 1; i > -1; i--){
//            ((TextView)layout3.getChildAt(i)).setText(tempe[tempe.length- 1 -i]);
//        }

        layout1.removeAllViews();
        layout2.removeAllViews();
        layout3.removeAllViews();

        String[] temps = begin.toString().split(",");
        for(int i = temps.length - 1; i > -1; i--){
            addText(temps[i], layout1);
        }

        String[] tempm = middle.toString().split(",");
        for (int i = tempm.length - 1; i > -1; i--) {
            addText(tempm[i], layout2);
        }

        String[] tempe = end.toString().split(",");
        for (int i = tempe.length - 1; i > -1; i--) {
            addText(tempe[i], layout3);
        }
        handler.sendEmptyMessageDelayed(0, 1000);
    }
}
