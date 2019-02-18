package com.further.run.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Hukuan
 * 2018/5/9.
 */
class VolatileTest {
    private static volatile int race = 0;
    private static final int THREADS_COUNT = 20;

    private synchronized static void increase() {
        race++;
    }

    public static void opreate() {
        Thread[] threads = new Thread[THREADS_COUNT];


        for (int j = 0; j < THREADS_COUNT; j++) {
            threads[j] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
//                        System.out.print("increase : " + race + "\n");
                    }
                }

            });
            threads[j].start();

        }
        while (Thread.activeCount() > 2) {
//            LogUtil.d("Thread.activeCount() : " + Thread.activeCount());
//            System.out.print("\nThread.activeCount() : " + Thread.activeCount());
            Thread.yield();//
        }
//        LogUtil.d("race : " + race);
        System.out.print("race opreate : " + race);
        race = 0;
    }


    public static void multiOperate() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increase();
                    System.out.print("increase : " + i + "\n");
                }
            }

        };
        for (int i = 0; i < 30; i++) {
            System.out.print("execute : " + i + "\n");
            executorService.execute(runnable);
        }

        executorService.shutdown();


        while (!executorService.isTerminated()) {
//            LogUtil.d("race : " + race);

        }
        System.out.print("race multiOperate : " + race);
        race = 0;

    }

    public static void futureOperate() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<FutureTask> futureTasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FutureTask futureTask = new FutureTask(new Callable() {//FutureTask只允许被执行一次
                @Override
                public Object call() throws Exception {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    return race;
                }
            });
            executorService.execute(futureTask);
            futureTasks.add(futureTask);
        }
        executorService.shutdown();

//        while (!futureTask.isDone()) {
//
//        }

        for (FutureTask task : futureTasks) {
//            while (!task.isDone()) {
//
//            }
            try {
                System.out.print("race multiOperate : " + task.get() + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.print("race multiOperate : " + race);
//        try {
//            System.out.print("race multiOperate : " + futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        race = 0;
//        opreate();
    }

    public static void futureOperate2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future> futureTasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Future future = executorService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    return race;
                }
            });
            futureTasks.add(future);
        }
        executorService.shutdown();

//        while (!futureTask.isDone()) {
//
//        }

        for (Future task : futureTasks) {
//            while (!task.isDone()) {
//
//            }
            try {
                System.out.print("race multiOperate : " + task.get() + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.print("race multiOperate : " + race);
//        try {
//            System.out.print("race multiOperate : " + futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        race = 0;
//        opreate();
    }

}
