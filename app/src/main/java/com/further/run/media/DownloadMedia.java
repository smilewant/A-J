package com.further.run.media;

import android.os.Environment;
import android.text.TextUtils;

import com.further.run.log.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * Created by Hukuan
 * 2019/1/8.
 */
public class DownloadMedia {

    public static void start() {
        for (int i = 0; i < 3; i++) {
            final int index = i;
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    String patchDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                    String fileUrl = Constant.getVideoInfo().get(index).getFilePath();
                    String version_jar = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
                    String pathName = patchDir + "/" + version_jar;
                    LogUtil.d("pathname : " + pathName);
                    FileOutputStream output = null;
                    try {
                        File dirFile = new File(patchDir);
                        File file = new File(pathName);
                        if (!TextUtils.isEmpty(fileUrl)) {
                            URL url = new URL(fileUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            InputStream input = conn.getInputStream();
//                        file.createNewFile();//新建文件
                            output = new FileOutputStream(file);
                            //读取大文件
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = input.read(buffer)) != -1) {
                                output.write(buffer, 0, len);
                            }
                            output.flush();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != output) {
                                output.flush();
                                output.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String patchDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//                String fileUrl = Constant.getVideoInfo().get(0).getFilePath();
//                String version_jar = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
//                String pathName = patchDir + "/" + version_jar;
//                LogUtil.d("pathname : " + pathName);
//                FileOutputStream output = null;
//                try {
//                    File dirFile = new File(patchDir);
//                    File file = new File(pathName);
//                    if (!TextUtils.isEmpty(fileUrl)) {
//                        URL url = new URL(fileUrl);
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                        InputStream input = conn.getInputStream();
////                        file.createNewFile();//新建文件
//                        output = new FileOutputStream(file);
//                        //读取大文件
//                        byte[] buffer = new byte[1024];
//                        int len;
//                        while ((len = input.read(buffer)) != -1) {
//                            output.write(buffer, 0, len);
//                        }
//                        output.flush();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        if (null != output) {
//                            output.flush();
//                            output.close();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}
