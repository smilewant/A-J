package com.further.foundation.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * create by qi.fang on 6:52 PM, Sun, 4/25/21
 * qi.fang@aihuishou.com
 * <p>
 * 描述：--
 **/
interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
