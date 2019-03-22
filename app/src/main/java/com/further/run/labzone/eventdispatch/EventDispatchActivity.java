package com.further.run.labzone.eventdispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.further.run.R;
import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/16.
 * View事件分发机制
 *
 * 1.事件最先传递到activity，由activity的dispatchTouchEvent对事件进行分发
 */
public class EventDispatchActivity extends AppCompatActivity {
    private CustomDispatchViewGroup outLayout;
    private CustomDispatchView innerView;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        initView();
        outDispatch();
//        innerDispatch();
    }

    private void initView() {
        outLayout = findViewById(R.id.out_layout);
        innerView = findViewById(R.id.inner_view);
//        innerView = new CustomDispatchView(this);
//        outLayout.addView(innerView);
        innerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                innerView = new CustomDispatchView(EventDispatchActivity.this);
//                outLayout.addView(innerView);
//                innerView.requestLayout();
                LogUtil.d("CustomDispatchView-------------------------");
                innerView.invalidate();
            }
        });

    }



    private void outDispatch() {
//        outLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        LogUtil.d("OUT ACTION_UP");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        LogUtil.d("OUT ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        LogUtil.d("OUT ACTION_DOWN");
//                        break;
//                }
//                return false;
//            }
//        });


    }

    private void innerDispatch() {
        innerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        LogUtil.d("INNER ACTION_UP");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.d("INNER ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        innerView.getParent().requestDisallowInterceptTouchEvent(true);
                        LogUtil.d("INNER ACTION_DOWN");
                        break;
                }
//                innerView.invalidate();
                return true;
            }
        });
        innerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("INNER OnClickListener");
            }
        });

        innerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LogUtil.d("INNER onLongClick");
                return true;
            }
        });
    }
}
