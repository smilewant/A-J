package com.further.run.designpatterns.proxymode;

import com.further.run.log.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Hukuan
 * 2018/5/7.
 */
public class DynamicProxySubject implements InvocationHandler {
    private Object object;
    public DynamicProxySubject() {
        super();
        this.object = new RealSubject();
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        long startTime = System.currentTimeMillis();
        method.invoke(object, objects);
        long stopTime = System.currentTimeMillis();
        LogUtil.d("反射调用时间：" + (stopTime - startTime) + "毫秒！");
        return null;
    }
}
