package com.further.run.anno;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.further.run.R;

/**
 * Created by Hukuan
 * 2018/5/18.
 */
@BindId(R.layout.activity_design_patterns)
public class AnnoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindIdApi.bindId(this);

    }
}
