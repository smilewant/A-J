package com.further.run.main;

import android.app.Application;
import android.content.Context;

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
}
