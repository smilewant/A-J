package com.further.run.main;

import android.content.Context;

import com.further.algorithm.AlgorithmDispatcher;
import com.further.foundation.BaseDispatcher;
import com.further.leetcode.LeetCodeDispatcher;

/**
 * Created by Zion
 * 2019/8/16.
 */
public class AppDispatcher extends BaseDispatcher {
    public static void initDispatcher() {
        gTotalDispatcher.put("Algorithm", new AlgorithmDispatcher());
        gTotalDispatcher.put("Leetcode", new LeetCodeDispatcher());
    }

    @Override
    public void startActivity(Context context, String className) {

    }
}
