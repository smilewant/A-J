package com.further.run.concurrent;

import com.further.foundation.util.LogUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hukuan
 * 2018/5/31.
 */
public class LockTest {
    private Lock lock = new ReentrantLock();
    public void lockMethod(Thread thread) {

            if (lock.tryLock()) {
                try {
                    LogUtil.d(thread.getName() + "持有锁");
                    thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                LogUtil.d( "锁被持有");
                lock.lock();

            }

    }

    public void synMethod(Thread thread) {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
//                LockTest.class.notify();
                LogUtil.d(thread.getName() + "持有锁 " + i);
                try {
//                    LockTest.class.wait(5000);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final void get(){}
}
