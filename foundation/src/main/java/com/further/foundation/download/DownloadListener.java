package com.further.foundation.download;

/**
 * 监听器
 * Created on 2019-10-17
 */
public interface DownloadListener {

     void onStart() ;

     void onSuccess(String url) ;

     void onError(String url, Throwable e) ;

     void onProgress(String url, long bytesRead, long contentLength, String speed) ;
}
