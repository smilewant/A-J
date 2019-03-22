package com.further.run.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.further.run.R;
import com.further.run.log.LogUtil;
import com.further.run.util.ProjectUtil;
import com.further.run.util.RVAdapter;
import com.further.run.util.RVHolder;

import static android.os.Build.CPU_ABI;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by Hukuan
 * 2018/4/26.
 */
public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(this.getTaskId() + " getName() " + this.getClass().getName() );
        initView();
        LogUtil.saveLog();
    }

    private void initView() {

        RecyclerView mMainRV = findViewById(R.id.main_recyler_view);

        mMainRV.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter mRvAdapter = new RVAdapter<Class<?> >(this, ProjectUtil.getClasses(), R.layout.item_main_rv) {
            @Override
            public int getLayoutResId(Class<?> data) {
                return !data.getName().contains("Design")? R.layout.item_main_rv : R.layout.item_main_rv;
            }

            @Override
            public void convert(RVHolder holder, final Class<?> data, int positon) {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < 1000; i++){
//                    stringBuilder.append(data.getName());
//                }
                holder.setText(R.id.content, data.getName());
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, data);
                        intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        try {
                            startActivity(intent);
                        } catch (Exception e){
                           startService(intent);
                        }


                    }
                });
            }
        };
        mMainRV.setAdapter(mRvAdapter);

//        isARMv7Compatible();
        LogUtil.e("isARMv7Compatible 1 " + Boolean.toString(isARMv7Compatible()));
    }


    public static boolean isARMv7Compatible() {
        try {
            if (SDK_INT >= LOLLIPOP) {
                for (String abi : Build.SUPPORTED_32_BIT_ABIS) {
                    if (abi.equals("armeabi-v7a")) {
                        return true;
                    }
                }
            } else {
                if (CPU_ABI.equals("armeabi-v7a") || CPU_ABI.equals("arm64-v8a")) {
                    return true;
                }
            }
        } catch (Throwable e) {
            LogUtil.e("isARMv7Compatible " + e.getMessage());
        }
        return false;
    }
}
