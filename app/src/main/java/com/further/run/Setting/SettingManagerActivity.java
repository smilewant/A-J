package com.further.run.Setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.further.run.R;
import com.further.run.customview.HomeCircleRoundImageView;

/**
 * Created by Zion
 * 2019/4/23.
 */
public class SettingManagerActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_manager);
        HomeCircleRoundImageView imageView = findViewById(R.id.iv_icon);
        Glide.with(this).load("https://pics.lvjs.com.cn/pics/lego/back/201904/1555924588303.jpg").placeholder(R.mipmap.lvmm_default_bg).override(100,100).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//        imageView.setBackgroundResource(R.mipmap.comm_avatar_circle_frame);

    }
}
