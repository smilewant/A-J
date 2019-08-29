package com.further.algorithm;

import android.content.Context;
import android.content.Intent;

import com.further.foundation.Dispatcher;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class AlgorithmDispatcher implements Dispatcher{
    public void startActivity(Context context, String className) {
        if ("AlgorithmActivity".equals(className)){
            context.startActivity(new Intent(context, AlgorithmActivity.class));
        }
    }
}
