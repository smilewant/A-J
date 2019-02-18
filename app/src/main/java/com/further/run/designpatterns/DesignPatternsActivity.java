package com.further.run.designpatterns;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.further.run.R;
import com.further.run.designpatterns.buildermode.Student;
import com.further.run.designpatterns.proxymode.DynamicProxySubject;
import com.further.run.designpatterns.proxymode.ProxySubject;
import com.further.run.designpatterns.proxymode.RealSubject;
import com.further.run.designpatterns.proxymode.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Hukuan
 * 2018/5/7.
 */
public class DesignPatternsActivity extends AppCompatActivity {
    private TextView staticAgentTV, dynaticAgentTV;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_patterns);

        staticAgentTV = findViewById(R.id.static_agent);
        dynaticAgentTV = findViewById(R.id.dynamic_agent);

        staticAgentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProxySubject proxySubject = new ProxySubject();
                proxySubject.visit();

                Student student = new Student();
                student.setID("what").setName("lihong").show();

                Student student1 = new Student();
                student1.setID("hello").show();
                student.show();

//                Map<String, String> a ;
//                HashMap<String, String> map = new HashMap<>();
//                map.put("1", "2");
//                map.get("2")
//                SparseArray array = new SparseArray();
//                array.put(1, "1");
//                ArrayMap<String, String> arrayMap = new ArrayMap<>();
//                arrayMap.put("1", "2");
            }
        });

        dynaticAgentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealSubject realSubject = new RealSubject();
                InvocationHandler invocationHandler = new DynamicProxySubject();
                Class<?> cls = realSubject.getClass();
                Subject subject = (Subject)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);
                subject.visit();
            }
        });
    }
}
