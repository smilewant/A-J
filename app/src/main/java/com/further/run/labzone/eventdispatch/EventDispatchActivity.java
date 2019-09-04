package com.further.run.labzone.eventdispatch;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.further.run.R;
import com.further.run.customview.SideBar;
import com.further.foundation.util.LogUtil;

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
    private SideBar sideBar;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        initView();
        outDispatch();
//        innerDispatch();
    }

    private void initView() {
        outLayout = findViewById(R.id.out_layout);
        outLayout.requestDisallowInterceptTouchEvent(false);
        innerView = findViewById(R.id.inner_view);
//        innerView = new CustomDispatchView(this);
//        outLayout.addView(innerView);
//        innerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                innerView = new CustomDispatchView(EventDispatchActivity.this);
////                outLayout.addView(innerView);
////                innerView.requestLayout();
//                LogUtil.e("INNER onClick------------------------");
//                innerView.invalidate();
//            }
//        });

        sideBar = findViewById(R.id.holiday_city_list_sidebar);

    }



    private void outDispatch() {
//        outLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:
//                        LogUtil.e("OUT ACTION_UP");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        LogUtil.e("OUT ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        LogUtil.e("OUT ACTION_DOWN");
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
                Toast.makeText(EventDispatchActivity.this, "event.getAction() : " + event.getAction(), Toast.LENGTH_SHORT).show();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        LogUtil.e("INNER ACTION_UP");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtil.e("INNER ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_DOWN:
//                        innerView.getParent().requestDisallowInterceptTouchEvent(true);
                        LogUtil.e("INNER ACTION_DOWN");
                        break;
                }
//                innerView.invalidate();
                return false;
            }
        });
        innerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("INNER OnClickListener");
            }
        });

        innerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LogUtil.e("INNER onLongClick");
                return true;
            }
        });
    }
}
