package com.further.x.http

import android.os.Message
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * Created by Zion
 * 2019/12/3.
 */
object HttpRequest {


    fun get(url: String, handler: ResponseHandler) {
        var client = OkHttpClient()//OkHttpClient 和 OkHttpClient().newBuilder().build()感觉并无区别，不知道为啥要写两种
        var request = Request.Builder().get().url(url).build()
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