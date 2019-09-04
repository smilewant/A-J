package com.further.run.main;

import android.app.Application;
import android.content.Context;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.further.run.rn.ScriptLoadUtil;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class FurApplication extends Application implements ReactApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDispatcher.initDispatcher();
        SoLoader.init(this, /* native exopackage */ false);
    }


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return ScriptLoadUtil.MULTI_DEBUG;//是否是debug模式
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @javax.annotation.Nullable
        @Override
        protected String getBundleAssetName() {
            return "platform.android.bundle";
        }

        @Override
        protected String getJSMainModuleName() {
            return "MultiDenugEntry";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
    // node  ./node_modules/react-native/local-cli/cli.js bundle --entry-file platformDep.js --platform android --dev false --bundle-output out/platform.android.bundle --assets-dest out --config .\platform.config.js
    // node  ./node_modules/react-native/local-cli/cli.js bundle --entry-file index.js --platform android --dev false --bundle-output out/index.android.bundle --assets-dest out --config .\buz.config.js

}
