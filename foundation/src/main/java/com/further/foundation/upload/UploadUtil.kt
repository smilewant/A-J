package com.further.foundation.upload

import android.util.Log
import androidx.annotation.NonNull
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

/**
 * create by qi.fang on 11:34 AM, Tue, 4/27/21
 * qi.fang@aihuishou.com
 *
 *
 * 描述：--
 */
object UploadUtil {
    var retrofit: Retrofit? = null
    fun  upload(uFile : File, md5: String, @NonNull uploadCallback: UploadCallback) {

        val body = RequestBody.create(MediaType.parse("multipart/form-data"), uFile)
        val part = MultipartBody.Part.createFormData(
            "file",
            uFile.name,
            body
        )

        val md5Part = MultipartBody.Part.createFormData("md5", md5)

        val bodyCall = retrofit!!.create(
            UploadService::class.java
        ).upload(part, md5Part)
        bodyCall!!.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Log.d("UploadUtil", "response s $response")
                if (response.code() == 200) {
                    uploadCallback.onSuccess()
                } else{
                    uploadCallback.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.d("UploadUtil", "response f $t")
                uploadCallback.onFail(t.toString())
            }
        })
    }

    fun init(@NonNull uploadCallback: UploadCallback) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(UploadInterceptor(object : UploadCallback {
                override fun onSuccess() {

                }

                override fun onFail(error: String) {

                }

                override fun onProgress(speed: Long, currentSize: Long) {
                    Log.d("UploadUtil", "response onProgress $speed  $currentSize")
                    uploadCallback.onProgress(speed, currentSize)
                }
            }))
            .build()
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://192.168.88.1/")
            .build()
    }

    fun getMd5(file: File?): String {
        var messageDigest: MessageDigest? = null
        val fileInputStream: FileInputStream
        val buffer = ByteArray(1024)
        var len: Int
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            fileInputStream = FileInputStream(file)
            while (fileInputStream.read(buffer, 0, 1024).also { len = it } != -1) {
                messageDigest.update(buffer, 0, len)
            }
            fileInputStream.close()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return if (messageDigest == null) {
            ""
        } else bytesToHexString(messageDigest.digest())
    }

    private fun bytesToHexString(bytes: ByteArray?): String {
        val sb = StringBuilder()
        if (bytes == null || bytes.isEmpty()) {
            return ""
        }
        for (i in bytes.indices) {
            val value: Int = bytes[i].toInt() and 0xff
            val hex = Integer.toHexString(value)
            if (hex.length < 2) {
                sb.append(0)
            }
            sb.append(hex)
        }
        return sb.toString()
    }
}