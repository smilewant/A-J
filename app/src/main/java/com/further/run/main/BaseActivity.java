package com.further.run.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.further.run.R;
import com.further.run.labzone.eventdispatch.CustomDispatchView;
import com.further.run.log.LogUtil;

import java.util.zip.Inflater;

/**
 * Created by Zion
 * 2019/7/17.
 */
public class BaseActivity extends AppCompatActivity {
    CustomDispatchView view;
    float rX = 0, ry = 0;
    float x,y;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        if (view == null) {
            view = new CustomDispatchView(this);
            view.setClickable(true);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:
//                performClick();
                            LogUtil.e("CustomDispatchView onTouchEvent ACTION_UP " + event.getAction());
                            break;
                        case MotionEvent.ACTION_MOVE:
                            x = event.getX() - rX;
                            y = event.getY() - ry;
                            view.x = x;
                            view.y = y;
                            LogUtil.e("CustomDispatchView onTouchEvent ACTION_MOVE " + event.getX() + " " + event.getRawX());
                            break;
                        case MotionEvent.ACTION_DOWN:
                            rX = event.getX();
                            ry = event.getY();
//                this.getParent().requestDisallowInterceptTouchEvent(true);
                            LogUtil.e("CustomDispatchView onTouchEvent ACTION_DOWN " + event.getX());
                            break;
                    }
                    view.invalidate();
                    return true;
                }
            });
//            view.setBackgroundColor(ContextCompat.getColor(this,R.color.color_dddddd));
            getDelegate().addContentView(view, new ViewGroup.LayoutParams(200, 200));
        }
    }
}
