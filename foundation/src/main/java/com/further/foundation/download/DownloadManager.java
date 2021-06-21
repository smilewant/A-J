package com.further.foundation.download;

import android.Manifest;
import android.app.Activity;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * create by zion.hu on 10:57 AM, Mon, 6/21/21
 * <p>
 * <p>
 * 描述：--  下载模块
 * github 替换
 *      api 'com.liulishuo.okdownload:okdownload:1.0.7'
 *     api 'com.liulishuo.okdownload:sqlite:1.0.7'
 *     api 'com.liulishuo.okdownload:okhttp:1.0.7'
 **/
class DownloadManager {
    public static void download(
            Activity activity,
            String url,
            String md5,
            String name,
            DownloadListener listener
    ) {

            File targetFile = new File(activity.getExternalCacheDir(), "/package/" + name);
            if (!targetFile.exists()) {
                return;
            }
            if (checkMD5(md5, targetFile)) {
                //如果 md5 值相同说明该文件已经存在，不需要重新下载
                return;
            }
            DownloadUtil.getInstance().download(url, targetFile);
            DownloadUtil.getInstance().addListener(listener);

    }

    public static boolean checkMD5(String md5, File file) {
        MessageDigest messageDigest = null;
        FileInputStream fileInputStream;
        byte buffer[] = new byte[1024];
        int len;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            while((len = fileInputStream.read(buffer, 0, 1024)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            fileInputStream.close();
        } catch (NoSuchAlgorithmException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (messageDigest == null) {
            return false;
        }
        return md5.equals(bytesToHexString(messageDigest.digest()));
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int value = bytes[i] & 0xff;
            String hex = Integer.toHexString(value);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
