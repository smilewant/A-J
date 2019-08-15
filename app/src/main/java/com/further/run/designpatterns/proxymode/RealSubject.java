package com.further.run.designpatterns.proxymode;

import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/7.
 */
public class RealSubject implements Subject {
    @Override
    public void visit() {
        LogUtil.d("i am real subject");
    }
}
