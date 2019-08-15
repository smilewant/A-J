package com.further.run.labzone.launchmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.further.run.R;
import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/28.
 */
public class SingleInstanceTestActivity extends AppCompatActivity {
    /*
    每次启动都会一个新的task，如果从这个跳转到其他的task，在其他task的activity返回的时候，会先让那个task的栈退出，退完后最后会返回到这个activity
     */
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_layout);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Single");
        LogUtil.d(this.getTaskId() + " getName() " + this.getClass().getName() );
//        findViewById(R.id.inner_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SingleInstanceTestActivity.this, SingleTaskTestActivity.class));
//            }
//        });
    }
}
