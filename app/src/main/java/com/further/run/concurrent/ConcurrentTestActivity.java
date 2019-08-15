package com.further.run.concurrent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.further.run.R;
import com.further.run.customview.LetterIndicator;
import com.further.foundation.util.LogUtil;

import run.further.com.annoprocessor.Name;

/**
 * Created by Hukuan
 * 2018/5/9.
 */
@Name(name = "Concurrent")
public class ConcurrentTestActivity extends AppCompatActivity implements View.OnClickListener {
    private LetterIndicator threadPool;
    private TextView threadLock;
    private TextView aftArraysTV;
    private TextView aft2ArraysTV;
    private LinearLayout linearLayout;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private long startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent_test);
        initView();
    }

    private void initView() {
        threadPool = findViewById(R.id.thread_pool);
        threadLock = findViewById(R.id.thread_lock);
        aftArraysTV = findViewById(R.id.aft_arrays);
        aft2ArraysTV = findViewById(R.id.aft2_arrays);
        linearLayout = findViewById(R.id.linner);
        threadPool.setOnClickListener(this);
        threadLock.setOnClickListener(this);
        aftArraysTV.setOnClickListener(this);
        aft2ArraysTV.setOnClickListener(this);

        preferences = getSharedPreferences("FURTHER", Context.MODE_PRIVATE);
        editor = preferences.edit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_id:
                VolatileTest.opreate();
                break;
            case R.id.thread_lock:
//                testApply();
                new LetterIndicator.TagLabelBuilder().name("12").create(this);
                threadPool.name("").text();
                linearLayout.addView(new LetterIndicator.TagLabelBuilder().name("12").create(this));
                break;
            case R.id.aft2_arrays:
                testCommit();
                break;
        }
    }

    private void lockOpreate() {
        final LockTest lockTest = new LockTest();
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                lockTest.lockMethod(Thread.currentThread());
                lockTest.synMethod(Thread.currentThread());
                lockTest.get();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                lockTest.lockMethod(Thread.currentThread());
                try {
                    t1.join();
                } catch (Exception e) {

                }
                lockTest.synMethod(Thread.currentThread());
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    private void testApply() {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    apply(1);
                    long endTime = System.currentTimeMillis();
                    long stamp = endTime - startTime;
                    LogUtil.d("testApply STAMP : " + stamp);
                }
            }).start();
        }

    }

    private void testCommit() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    get();
                }
            }).start();
        }
        long endTime = System.currentTimeMillis();
        long stamp = endTime - startTime;
        LogUtil.d("testCommit STAMP : " + stamp);
    }

    private void apply(int i) {
        editor.putString("test", i + "");
        editor.apply();
    }

    private void commit(int i) {
        editor.putString("test", i + "");
        editor.commit();
    }

    private void get() {
//        preferences.getString("test", "-1");


    }

}
