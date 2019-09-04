package com.further.run.anno;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
