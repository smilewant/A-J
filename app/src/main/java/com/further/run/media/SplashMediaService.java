package com.further.run.media;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.further.foundation.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;


/**
 * Created by Hukuan
 * 2019/1/11.
 */
public class SplashMediaService extends IntentService {
    private static final String TAG = "SplashMediaService";

    private static final String MEDIA_LIST = "media_list";

    public SplashMediaService() {
        super(SplashMediaService.class.getSimpleName());
    }

    public static void runSplashMediaService(Context context, List<BootAnimResponse.BootAnimModel> mediaList) {
        try {
            Intent intent = new Intent(context, SplashMediaService.class);
            intent.putExtra(MEDIA_LIST, (Serializable) mediaList);
            context.startService(intent);
        } catch (Throwable throwable) {
            LogUtil.e(TAG, "start SplashMediaService fail, exception:" + throwable);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            LogUtil.e(TAG, "SplashMediaService received a null intent, ignoring.");
            return;
        }
        final List<BootAnimResponse.BootAnimModel> mediaList = getMediaList(intent);
        if (mediaList == null || mediaList.size() < 1) {
            LogUtil.e(TAG, "SplashMediaService can't get the media list extra, ignoring.");
            return;
        }
        LogUtil.e(TAG, "start onHandleIntent, exception:" + mediaList);
        final String saveDir = getPatchDir(this).getAbsolutePath();

        for (int i = 0; i < mediaList.size(); i++) {
            final int index = i;
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    String mediaUrl = mediaList.get(index).videoUrl;
                    String mediaName = mediaList.get(index).name.concat("_").concat(mediaList.get(index).endTime.substring(0, 10)).concat(".mp4");
                    String pathName = saveDir + "/" + mediaName;
                    File file = new File(pathName);
                    if (!file.exists()) {
                        FileOutputStream output = null;
                        try {

                            if (!TextUtils.isEmpty(mediaUrl)) {
                                URL url = new URL(mediaUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                InputStream input = conn.getInputStream();
                                file.createNewFile();//新建文件
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

                }
            });
        }

        //删除视频
        for (final File file : getPatchDir(this).listFiles()) {
            LogUtil.e(TAG, "start onHandleIntent file:" + file.getName());
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (file.getName().contains(".mp4") && file.getName().contains("_")) {
                        String endTime = file.getName().split("_")[1];
                        endTime = endTime.substring(0, endTime.length() - 4);
                        if (isOutTime(endTime) || endTime.contains(" ")) {
                            file.delete();
                        }
                    }

                    String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
                    for (File mFile : Environment.getExternalStorageDirectory().listFiles()) {
                        if (mFile.getName().contains(".mp4")) {
                            copyToFile(mFile.getAbsolutePath(), file.getParentFile().getAbsolutePath());
                        }
                    }
                }
            });
        }
    }

    private void copyToFile(String fromFile, String toFile) {//https://blog.csdn.net/javasxl/article/details/71524831
        FileInputStream fosfrom = null;
        FileOutputStream fosto = null;
        try {
            fosfrom = new FileInputStream(fromFile);
            fosto = new FileOutputStream(toFile + "/" + fromFile.substring(fromFile.length() - 10, fromFile.length()));
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosto.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fosto) {
                    fosto.flush();
                    fosto.close();
                }

                if (null != fosfrom) {
                    fosfrom.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private boolean isOutTime(String endTime) {
        if (TextUtils.isEmpty(endTime)) {
            return false;
        }
        Date dateNow = new Date();
        Date dateEnd = mParse(endTime, "yyyy-MM-dd");
        return dateEnd != null && dateEnd.before(dateNow);
    }

    public static List<BootAnimResponse.BootAnimModel> getMediaList(Intent intent) {
        if (intent == null) {
            return null;
        }

        List<BootAnimResponse.BootAnimModel> ret;
        try {
            ret = (List<BootAnimResponse.BootAnimModel>) intent.getSerializableExtra(MEDIA_LIST);
        } catch (Exception e) {
            LogUtil.e(TAG, "getMediaList exception:" + e.getMessage());
            ret = null;
        }
        return ret;
    }

    public static File getPatchDir(Context context) {
        File dir = context.getDir("dl_patch", Context.MODE_PRIVATE);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public Date mParse(String str, String pattern) {
        try {
            return returnSimple(pattern).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SimpleDateFormat returnSimple(String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault());
    }

}

