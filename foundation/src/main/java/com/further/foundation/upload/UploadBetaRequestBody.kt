package com.further.foundation.upload

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

/**
 * create by zion.hu on 12:42 PM, Fri, 4/30/21
 *
 *
 * 描述：--
 **/
class UploadBetaRequestBody(
    body: RequestBody?,
    uploadCallback: UploadCallback
) : RequestBody() {
    private val mBody = body
    private val mUploadCallback = uploadCallback
    private var bufferedSink : BufferedSink ?= null
    override fun contentType(): MediaType? {
        return mBody?.contentType()
    }

    override fun contentLength(): Long {
        return mBody?.contentLength()!!
    }

    override fun writeTo(sink: BufferedSink) {

        if (bufferedSink == null) {
            bufferedSink = sink(sink).buffer()
        }
        mBody?.writeTo(bufferedSink)
        bufferedSink?.flush()
    }

    private fun sink(sink: BufferedSink): Sink {
        return object : ForwardingSink(sink) {

            var byteSpeed = 0L
            var beforeTime = 0L
            var speed = 0L
            @Override
            override fun write(source : Buffer, byteCount : Long) {


                byteSpeed += byteCount

                if (System.currentTimeMillis() - beforeTime > 0) {
                    speed = byteSpeed / (System.currentTimeMillis() - beforeTime)
                    byteSpeed = 0L
                    beforeTime = System.currentTimeMillis()
                }
                mUploadCallback.onProgress(speed * 1000, byteCount)
                super.write(source, byteCount)
            }
        }
    }


}