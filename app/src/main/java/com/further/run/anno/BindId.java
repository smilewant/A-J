package com.further.run.anno;

import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Hukuan
 * 2018/5/18.
 */
@Retention(RetentionPolicy.RUNTIME)
//@Target({FIELD, TYPE})
public @interface BindId {
    int value() default View.NO_ID;
}
