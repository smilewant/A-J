package com.further.run.labzone.eventdispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.further.run.R;
import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/16.
 */
public class CustomDispatchView extends View {
    public float x, y;
    private Paint paint = new Paint();

    public CustomDispatchView(Context context) {
        super(context);
    }

    public CustomDispatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDispatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                LogUtil.e("CustomDispatchView dispatchTouchEvent ACTION_UP " + event.getAction());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                LogUtil.e("CustomDispatchView dispatchTouchEvent ACTION_MOVE " + event.getAction());
//                break;
//            case MotionEvent.ACTION_DOWN:
//                LogUtil.e("CustomDispatchView dispatchTouchEvent ACTION_DOWN " + event.getAction());
////                return true;
//                break;
//        }
//        return super.dispatchTouchEvent(event);
////        return true;
//    }

    float rX = 0, ry = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                LogUtil.e("CustomDispatchView onTouchEvent ACTION_UP requestFocus " + Boolean.toString(requestFocus()));
//                performClick();
                LogUtil.e("CustomDispatchView onTouchEvent ACTION_UP " + event.getAction());
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX() - rX;
                y = event.getY() - ry;
                LogUtil.e("CustomDispatchView onTouchEvent ACTION_MOVE " + event.getX() + " " + event.getRawX());
                break;
            case MotionEvent.ACTION_DOWN:
                rX = event.getX();
                ry = event.getY();
//                this.getParent().requestDisallowInterceptTouchEvent(true);
                LogUtil.e("CustomDispatchView onTouchEvent ACTION_DOWN " + event.getX());
                break;
        }
//        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        LogUtil.e("performClick ");
        return super.performClick();
    }

    protected void onDraw(Canvas canvas) {
        LogUtil.d("CustomDispatchView onDraw");
        if ( x > y ) {
            paint.setColor(getResources().getColor(R.color.color_D30775));
        } else {
            paint.setColor(getResources().getColor(R.color.color_0000000));
        }
        canvas.drawCircle(200 + x, 200 + y, 50, paint);
        canvas.drawCircle(100 + x, 100 + y, 50, paint);
        canvas.drawCircle(50 + x, 50 + y, 50, paint);
        paint.setTextSize(122);
        canvas.drawText("大", 200, 200 , paint);
        this.invalidate();
        super.onDraw(canvas);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        LogUtil.d("CustomDispatchView onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void onLayout(boolean changed, int l, int t, int r, int b){
        LogUtil.d("CustomDispatchView onLayout");
        super.onLayout(changed, l, t, r, b);
    }
}
