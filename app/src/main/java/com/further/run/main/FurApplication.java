package com.further.run.main;

import android.app.Application;


import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class FurApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDispatcher.initDispatcher();

    }





    // node  ./node_modules/react-native/local-cli/cli.js bundle --entry-file platformDep.js --platform android --dev false --bundle-output out/platform.android.bundle --assets-dest out --config .\platform.config.js
    // node  ./node_modules/react-native/local-cli/cli.js bundle --entry-file index.js --platform android --dev false --bundle-output out/index.android.bundle --assets-dest out --config .\buz.config.js

}
