package com.further.run.rn;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.AsyncReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.further.run.BuildConfig;

/**
 * Created by Zion
 * 2019/8/30.
 */
public class MyReactActivity extends AsyncReactActivity {
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "reactnative_multibundler";
    }


    @Override
    protected String getScriptPath() {
        return "index.android.bundle";
    }

    @Override
    protected AsyncReactActivity.ScriptType getScriptPathType() {
        return ScriptType.ASSET;
    }
}