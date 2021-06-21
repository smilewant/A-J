package com.further.foundation.upload

/**
 * create by qi.fang on 3:56 PM, Wed, 4/28/21
 * qi.fang@aihuishou.com
 *
 * 描述：--
 **/
interface UploadCallback{
    fun onSuccess()
    fun onFail(error : String)
    fun onProgress(speed : Long, currentSize : Long)
}