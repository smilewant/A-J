package com.further.run.labzone.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/5/21.
 */
public class AidlService extends Service {
    private IMyAidlInterface aidlInterface;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return service;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("onStartCommand");

        return flags;
    }



    private IMyAidlService.Stub service = new IMyAidlService.Stub() {
        @Override
        public void perform(String aString) throws RemoteException {
            aidlInterface.basicTypes(aString);
        }

        @Override
        public void register(IMyAidlInterface i) throws RemoteException {
            aidlInterface = i;
        }
    };
}
