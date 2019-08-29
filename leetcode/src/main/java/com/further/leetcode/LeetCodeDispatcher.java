package com.further.leetcode;

import android.content.Context;
import android.content.Intent;

import com.further.foundation.Dispatcher;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class LeetCodeDispatcher implements Dispatcher {
    @Override
    public void startActivity(Context context, String className) {
        if ("LeetCodeActivity".equals(className)) {
            context.startActivity(new Intent(context, LeetCodeActivity.class));
        }
    }
}
