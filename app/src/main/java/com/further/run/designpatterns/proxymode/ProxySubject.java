package com.further.run.designpatterns.proxymode;

import run.further.com.annoprocessor.Name;

/**
 * Created by Hukuan
 * 2018/5/7.
 */
@Name(name ="Concurrent")
public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(){
        this.subject = new RealSubject();
    }

    @Override
    public void visit() {
        subject.visit();
    }
}
