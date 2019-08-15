package com.further.run.labzone.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.further.run.R;
import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/28.
 */
public class SingleTaskTestActivity extends AppCompatActivity {
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task_test);
        LogUtil.d(this.getTaskId() + " getName() " + this.getClass().getName() );
        findViewById(R.id.inner_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskTestActivity.this, SingleInstanceTestActivity.class));
            }
        });
    }
}
