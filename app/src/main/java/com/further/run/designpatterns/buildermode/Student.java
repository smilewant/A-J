package com.further.run.designpatterns.buildermode;

import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/6/12.
 */
public class Student {
    private String id;
    private String name;
    public Student setID(String id){
        this.id = id;
        return this;
    }

    public Student setName(String name){
        this.name = name;
        return this;
    }
    public void show(){
        LogUtil.d("Student : " + id);
    }

}
