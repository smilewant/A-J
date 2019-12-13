package com.further.x.http

import android.os.Message
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Zion
 * 2019/12/3.
 *
 * 缓存
 */
object HttpRequest {
    private var cache : Cache ?= null
    fun setC(cache : Cache) {
        this.cache = cache
    }
    fun get(url: String, handler: ResponseHandler) {
        var client = OkHttpClient().newBuilder().cache(cache).build();//OkHttpClient 和 OkHttpClient().newBuilder().build()感觉并无区别，不知道为啥要写两种

        var request = Request.Builder().get().url(url)
                .cacheControl(CacheControl.Builder()
//                        .onlyIfCached()
                .maxStale(60, TimeUnit.MINUTES)
                .maxAge(5, TimeUnit.DAYS).build())
                .build()
        var call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                var msg = Message()
                msg.what = 0
                handler.sendMessage(msg)
            }

            override fun onResponse(call: Call?, response: Response?) {
                response?.let {
                    var msg = Message()
                    msg.obj = String(getBytes(it))
                    msg.what = 1
                    handler.sendMessage(msg)
                }
            }

        })

    }


    fun getBytes(response: Response): ByteArray {
        var bytes: ByteArray
        var buffer = Buffer()
        var source = response.body().source()
        var readCount: Long
        do readCount = source.read(buffer, 8192) while (readCount.compareTo(-1) != 0)

        bytes = buffer.readByteArray()
        return bytes
    }


}