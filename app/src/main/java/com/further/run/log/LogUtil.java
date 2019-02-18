package com.further.run.log;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Hukuan
 * 2018/4/28.
 */
public class LogUtil {
    private static final String TAG = "furtherrun";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void v(String msg) {
        Log.v(TAG, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void saveLog() {//把日志打印出来
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            OutputStreamWriter inputStreamReader = new OutputStreamWriter(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lvmama/log1.txt")));

            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
                inputStreamReader.write(line);
            }

            File hashFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lvmama/log.txt");
            FileWriter writer = new FileWriter(hashFile, true);
            writer.write(log.toString());

        } catch (IOException e) {

        }
    }
}
