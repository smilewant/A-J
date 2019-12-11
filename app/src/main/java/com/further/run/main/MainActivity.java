package com.further.run.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.further.foundation.BaseActivity;
import com.further.run.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;
import com.further.run.util.ProjectUtil;
import com.further.foundation.adapter.RVAdapter;
import com.further.foundation.adapter.RVHolder;

import java.io.File;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Hukuan
 * 2018/4/26.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.d(this.getTaskId() + " getName() " + this.getClass().getName());
        LogUtil.d(this.getTaskId() + " getDir() " + this.getExternalCacheDir());

        initView();
        try {
            macAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        String sdPath = getExternalCacheDir().getAbsolutePath();
        File file = new File(sdPath + "/", "post1.png");
        file.mkdir();
        for (File file1 : getCacheDir().listFiles()) {
            LogUtil.e("getAbsolutePath : " + file1.getAbsolutePath());
        }
        requestPermission();
        getSerial();


        StringBuilder url = new StringBuilder();
        url.append("data:image/png;base64");


        ImageView img = findViewById(R.id.img);
        Glide.with(this).load(url.toString()).into(img);
//        DevicePolicyManager policyManager = getSystemService(DevicePolicyManager);
//        policyManager.getWifiMacAddress()

//        file.mkdir();
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(file, true);
//            writer.write("111");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        LogUtil.saveLog();

        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        new Thread(task).start();
        Future future = new Future() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return null;
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();

    }

    private void initView() {
        TextView txt = findViewById(R.id.txt);
        new Thread(new Runnable() {
            @Override
            public void run() {
                txt.setText("change");
            }
        }).start();

        RecyclerView mMainRV = findViewById(R.id.main_recyler_view);

        mMainRV.setLayoutManager(new GridLayoutManager(this, 2));
        mMainRV.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,0,MobileUtil.dip2px(10),MobileUtil.dip2px(10));
            }
        });
        RVAdapter mRvAdapter = new RVAdapter<Class<?>>(this, ProjectUtil.getClasses(), R.layout.item_main_rv) {
            @Override
            public int getLayoutResId(Class<?> data) {
                return !data.getName().contains("Design") ? R.layout.item_main_rv : R.layout.item_main_rv;
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
                        if (data.getName().contains("Service")){
                            startService(intent);

                        } else {
                            intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                            overridePendingTransition(R.anim.dt_in_from_left, R.anim.dt_out_to_left);


                        }

                    }
                });
            }
        };
        mMainRV.setAdapter(mRvAdapter);

//        isARMv7Compatible();
        LogUtil.e("isARMv7Compatible 1 " + Boolean.toString(MobileUtil.isARMv7Compatible()));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //有悬浮窗权限开启服务绑定 绑定权限

                }
            }
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            String sdPath = getExternalCacheDir().getAbsolutePath();
            File file = new File(sdPath + "/", "post.png");
            file.mkdir();
            // permission was granted, yay! Do the
            // contacts-related task you need to do.

        } else {

            // permission denied, boo! Disable the
            // functionality that depends on this permission.
        }
    }

    public String macAddress() throws SocketException {
        String address = null;
        // 把当前机器上的访问网络接口的存入 Enumeration集合中
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface netWork = interfaces.nextElement();
            // 如果存在硬件地址并可以使用给定的当前权限访问，则返回该硬件地址（通常是 MAC）。
            byte[] by = netWork.getHardwareAddress();
            if (by == null || by.length == 0) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (byte b : by) {
                builder.append(String.format("%02X:", b));
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            String mac = builder.toString();
            LogUtil.e("interfaceName=" + netWork.getName() + ", mac=" + mac);
            // 从路由器上在线设备的MAC地址列表，可以印证设备Wifi的 name 是 wlan0
            if (netWork.getName().equals("wlan0")) {
                LogUtil.e(" interfaceName =" + netWork.getName() + ", mac=" + mac);
                address = mac;
            }
        }
        return address;
    }

    public void getSerial() {
        String serial;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                serial = android.os.Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        LogUtil.e(" interfaceName serial =" + serial);

    }

}
