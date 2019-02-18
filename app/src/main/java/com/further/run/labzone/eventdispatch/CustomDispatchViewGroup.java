package com.further.run.labzone.eventdispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/16.
 */
public class CustomDispatchViewGroup extends RelativeLayout {
    public CustomDispatchViewGroup(Context context) {
        super(context);
    }

    public CustomDispatchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                LogUtil.d("CustomDispatchViewGroup dispatchTouchEvent ACTION_UP " + ev.getAction());
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d("CustomDispatchViewGroup dispatchTouchEvent ACTION_MOVE " + ev.getAction());
                break;
            case MotionEvent.ACTION_DOWN:
                LogUtil.d("CustomDispatchViewGroup dispatchTouchEvent ACTION_DOWN " + ev.getAction());
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                LogUtil.d("CustomDispatchViewGroup onInterceptTouchEvent ACTION_UP " + ev.getAction());
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d("CustomDispatchViewGroup onInterceptTouchEvent ACTION_MOVE " + ev.getAction());
//                return true;
                break;
            case MotionEvent.ACTION_DOWN:
                LogUtil.d("CustomDispatchViewGroup onInterceptTouchEvent ACTION_DOWN " + ev.getAction());
                break;
        }
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                LogUtil.d("CustomDispatchViewGroup onTouchEvent ACTION_UP " + ev.getAction());
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.d("CustomDispatchViewGroup onTouchEvent ACTION_MOVE " + ev.getAction());
                break;
            case MotionEvent.ACTION_DOWN:
                LogUtil.d("CustomDispatchViewGroup onTouchEvent ACTION_DOWN " + ev.getAction());
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void onMeasure(int widthSpec, int heightSpec){
        LogUtil.d("CustomDispatchViewGroup onMeasure");
        super.onMeasure(widthSpec, heightSpec);
    }

    public void onDraw(Canvas c){
        LogUtil.d("CustomDispatchViewGroup onDraw");
        super.onDraw(c);
    }

    public void onLayout(boolean changed, int l, int t, int r, int b){
        LogUtil.d("CustomDispatchViewGroup onLayout");
        super.onLayout(changed, l, t, r, b);
    }
}
