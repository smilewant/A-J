package com.further.foundation;

import android.os.Bundle;

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
}
