package com.further.run.labzone.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.further.run.R;
import com.further.foundation.util.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/21.
 */
public class AidlActivity extends AppCompatActivity {
    private IMyAidlService aidlService;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AidlActivity.this, AidlService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//                startService(intent);

//                Intent intent1 = new Intent("com.custom.service");
//
//                PackageManager pm = getPackageManager();
//                List<ResolveInfo> resolveInfo = pm.queryIntentServices(intent1, 0);
//
//                // Make sure only one match was found
//                if (resolveInfo == null || resolveInfo.size() != 1) {
//                    return;
//                }
//
//                // Get component info and create ComponentName
//                ResolveInfo serviceInfo = resolveInfo.get(0);
//                String packageName = serviceInfo.serviceInfo.packageName;
//                String className = serviceInfo.serviceInfo.name;
//                ComponentName component = new ComponentName(packageName, className);
//                LogUtil.d("basicTypes " + packageName +" "+  className);


//                Intent intent2 = new Intent();
//                ComponentName componentName = new ComponentName("higo.com.higo","com.higo.HiGoAidlService");
//                intent2.setComponent(componentName);
//                startService(intent2);
//                bindService(intent2, mConnection, Context.BIND_AUTO_CREATE);

            }
        });
        findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
            }
        });
        findViewById(R.id.dynamic_agent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    aidlService.perform("what");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    IMyAidlInterface myAidlInterface = new IMyAidlInterface.Stub(){

        @Override
        public void basicTypes(String aString) throws RemoteException {
            LogUtil.d("basicTypes " + aString);
        }
    };



    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlService = IMyAidlService.Stub.asInterface(service);
            if (aidlService == null) throw new NumberFormatException();
            try {
                aidlService.register(myAidlInterface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlService = null;
        }
    };
}
