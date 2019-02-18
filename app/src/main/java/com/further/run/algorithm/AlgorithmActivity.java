package com.further.run.algorithm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.further.run.R;

import java.util.Random;

/**
 * Created by Hukuan
 * 2018/4/26.
 */
public class AlgorithmActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] arrays = new int[20];
    private TextView generateTV;
    private TextView currentArraysTV;
    private TextView aftArraysTV;
    private TextView aft2ArraysTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        initView();
    }

    private void initView() {
        generateTV = findViewById(R.id.generate_id);
        currentArraysTV = findViewById(R.id.current_arrays);
        aftArraysTV = findViewById(R.id.aft_arrays);
        aft2ArraysTV = findViewById(R.id.aft2_arrays);
        generateTV.setOnClickListener(this);
        aftArraysTV.setOnClickListener(this);
        aft2ArraysTV.setOnClickListener(this);
    }

    private void generateEvent() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 20; i++) {
            arrays[i] = random.nextInt(100);
        }
        currentArraysTV.setText(displayArray(arrays));

        FindCommonNumber.find();
    }

    private void sortEvent(int[] arrays) {
        QuickSortUtil.sort(arrays, 0, arrays.length-1);
        aftArraysTV.setText(displayArray(arrays));
    }

    private void sort2Event(int[] arrays) {
        InsertionSort.sort(arrays);
        aft2ArraysTV.setText(displayArray(arrays));
    }

    private String displayArray(int[] arrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int a : arrays){
            stringBuilder.append(a).append(",");
        }
        return stringBuilder.toString();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.generate_id:
                generateEvent();
                break;
            case R.id.aft_arrays:
                sortEvent(arrays);
                break;
            case R.id.aft2_arrays:
                sort2Event(arrays);
                break;
        }
    }
}
