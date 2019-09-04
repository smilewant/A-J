package com.further.run.designpatterns;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.further.run.R;
import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/7.
 */
public class DesignPatternsActivity extends AppCompatActivity {
    private TextView staticAgentTV, dynaticAgentTV;
    private SlidingPaneLayout slidingPaneLayout;
    private LinearLayout linearLayout;
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_patterns);
        findViewById(R.id.open_sliding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingPaneLayout.isOpen()){
                    slidingPaneLayout.closePane();
                } else{
                    slidingPaneLayout.openPane();
                }
            }
        });
        slidingPaneLayout = findViewById(R.id.sliding_pane_layout);
////        slidingPaneLayout.openPane();
        linearLayout = findViewById(R.id.left_layout);
        linearLayout.setAlpha(0);
        linearLayout.setVisibility(View.INVISIBLE);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View view, float v) {
                linearLayout.setAlpha(v);
                LogUtil.e("onPanelSlide");
            }

            @Override
            public void onPanelOpened(@NonNull View view) {
                LogUtil.e("onPanelOpened");
                linearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPanelClosed(@NonNull View view) {
                LogUtil.e("onPanelClosed");
                linearLayout.setVisibility(View.INVISIBLE);

            }
        });
//
//        staticAgentTV = findViewById(R.id.static_agent);
//        dynaticAgentTV = findViewById(R.id.dynamic_agent);

//        staticAgentTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                ProxySubject proxySubject = new ProxySubject();
//                proxySubject.visit();
//
//                Student student = new Student();
//                student.setID("what").setName("lihong").show();
//
//                Student student1 = new Student();
//                student1.setID("hello").show();
//                student.show();
//
////                Map<String, String> a ;
////                HashMap<String, String> map = new HashMap<>();
////                map.put("1", "2");
////                map.get("2")
////                SparseArray array = new SparseArray();
////                array.put(1, "1");
////                ArrayMap<String, String> arrayMap = new ArrayMap<>();
////                arrayMap.put("1", "2");
//            }
//        });
//
//        dynaticAgentTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RealSubject realSubject = new RealSubject();
//                InvocationHandler invocationHandler = new DynamicProxySubject();
//                Class<?> cls = realSubject.getClass();
//                Subject subject = (Subject)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);
//                subject.visit();
//            }
//        });
    }
}
