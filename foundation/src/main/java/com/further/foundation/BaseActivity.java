package com.further.foundation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.further.foundation.dialog.BaseDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by Zion
 * 2019/7/17.
 */
public class BaseActivity extends AppCompatActivity {
    private BaseDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        statusBarInit();
    }

    public void onResume(){
        super.onResume();
    }

    public void dialogDismiss() {
        if (loadingDialog != null ) {
            loadingDialog.dismiss();
        }
    }

    public void dialogShow() {
        if (loadingDialog == null) {
            loadingDialog = new BaseDialog();
        }
        loadingDialog.show(getSupportFragmentManager(), "loading");
    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.dt_in_from_left, R.anim.dt_out_to_right);
    }

    public void statusBarInit() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor( Color.TRANSPARENT);
//            val window = getWindow()
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            val decorView = window.getDecorView()
//            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                if (percent > 200) {//和statusbar字体颜色相关
////                    option = option or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                } else {
////                    option = option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
////                }
//                option = option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                decorView.setSystemUiVisibility(option)
//                //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                window.setStatusBarColor(Color.argb(255,  0xff, 0xff, 0xff))
//            }

    }
}
