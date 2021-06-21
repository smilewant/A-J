package com.further.foundation.upload

import com.further.foundation.upload.UploadBetaRequestBody
import com.further.foundation.upload.UploadCallback
import okhttp3.Interceptor
import okhttp3.Response

/**
 * create by zion.hu on 6:55 PM, Fri, 4/30/21
 *
 *
 * 描述：--
 **/
class UploadInterceptor(uploadCallback: UploadCallback) : Interceptor{
    private val mUploadCallback = uploadCallback
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val build =  request.newBuilder().post(UploadBetaRequestBody(request.body(), mUploadCallback))
            .addHeader("Connection", "close").addHeader("Accept-Encoding", "identity").build()
        return chain.proceed(build)
    }
}