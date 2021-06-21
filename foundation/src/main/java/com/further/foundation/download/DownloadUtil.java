package com.further.foundation.download;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 下载中心
 * Created on 2019-10-16
 */
public class DownloadUtil {
    private static final String TAG = "rustAppDownloadCenter";

    private static DownloadUtil instance;

    private static Retrofit retrofit;

    private Set<String> urls = new HashSet();
    private Set<DownloadListener> listeners = new HashSet<>();

    private DownloadUtil() {
        init();
    }

    public static DownloadUtil getInstance() {
        if (instance == null) {
            synchronized (DownloadUtil.class) {
                if (instance == null) {
                    instance = new DownloadUtil();
                }
            }
        }
        return instance;
    }


    public void download(final String downUrl, final File targetFile) {
        //检查当前文件是否存在，如果存在，校验 md5 值，如果相同直接返回
        //如果当前 url 正在下载 return
        if (urls.contains(downUrl)) {
            return;
        }
        tellDownloadStart(downUrl);
        retrofit.create(DownloadService.class)
                .download(downUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        saveFile(responseBody, targetFile, downUrl);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept on error: " + downUrl, throwable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                        tellDownloadError(downUrl, e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(new ProgressInterceptor(new ProgressListener() {
                    @Override
                    public void update(String url, long bytesRead, long contentLength, String speed) {
                        tellProgress(url, bytesRead, contentLength, speed);
                    }
                }))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://yourbaseurl.com")
                .build();
    }

    public void saveFile(ResponseBody body, File targetFile, String url) {
        File tmpFile = new File(targetFile.getAbsolutePath() + ".tmp");
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        InputStream srcInputStream = null;
        try {
            Log.d(TAG, "saveFile: body content length: " + body.contentLength());
            srcInputStream = body.byteStream();
            File dir = tmpFile.getParentFile();
            if (dir == null) {
                throw new FileNotFoundException("target file has no dir.");
            }
            if (!dir.exists()) {
                 dir.mkdirs();
            }
            File file = tmpFile;
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            while ((len = srcInputStream.read(buf)) != -1  ) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            if (tmpFile.renameTo(targetFile)) {
                tellDownloadSuccess(url);
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG, "saveFile: FileNotFoundException ", e);

            tellDownloadError(url, e);
        } catch (Exception e) {
            Log.e(TAG, "saveFile: IOException ", e);

            tellDownloadError(url, e);
        } finally {
            try {
                if (srcInputStream != null) {
                    srcInputStream.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "saveFile", e);
            }

        }
    }

    public void addListener(DownloadListener l) {
        listeners.add(l);
    }

    public void removeListener(DownloadListener l) {
        listeners.remove(l);
    }

    private void tellDownloadSuccess(String url) {
        urls.remove(url);
        for (DownloadListener l : listeners) {
            l.onSuccess(url);
        }
    }

    private void tellDownloadError(String url, Throwable e) {
        urls.remove(url);
        for (DownloadListener l : listeners) {
            l.onError(url, e);
        }
    }

    private void tellProgress(String url, long bytesRead, long contentLength, String speed) {
        for (DownloadListener l : listeners) {
            l.onProgress(url, bytesRead, contentLength, speed);
        }
    }


    private void tellDownloadStart(String url ) {
        urls.add(url);
        for (DownloadListener l : listeners) {
            l.onStart( );
        }
    }
}

