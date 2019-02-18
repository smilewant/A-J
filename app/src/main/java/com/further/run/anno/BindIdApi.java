package com.further.run.anno;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * Created by Hukuan
 * 2018/5/18.
 */
public class BindIdApi {
    public static void bindId(Activity obj) {
        Class<?> cls = obj.getClass();
        if (cls.isAnnotationPresent(BindId.class)) {
            BindId mId = cls.getAnnotation(BindId.class);
            int id = mId.value();

            try {
                Method method = cls.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(obj, id);
            } catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
