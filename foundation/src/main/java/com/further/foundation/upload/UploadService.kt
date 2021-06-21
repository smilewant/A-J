package com.further.foundation.upload

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * create by qi.fang on 11:01 AM, Tue, 4/27/21
 * qi.fang@aihuishou.com
 *
 *
 * 描述：--
 */
internal interface UploadService {
    @Multipart
    @POST("jdx-inspection/upload.jdx")
    fun upload(
        @Part file: MultipartBody.Part?,
        @Part md5: MultipartBody.Part?
    ): Call<ResponseBody?>?
}