package com.further.foundation;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class BaseDispatcher implements Dispatcher {
    public static HashMap<String, Dispatcher> gTotalDispatcher = new HashMap<>();

    @Override
    public void startActivity(Context context, String className) {
        String[] name = className.split("/");
        Dispatcher jumpDispatcher = gTotalDispatcher.get(name[0]);
        if (jumpDispatcher != null) jumpDispatcher.startActivity(context, name[1]);
    }
}
