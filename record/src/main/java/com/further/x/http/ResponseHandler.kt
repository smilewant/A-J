package com.further.x.http

import android.os.Handler
import android.os.Looper
import android.os.Message


/**
 * Created by Zion
 * 2019/12/3.
 */
class ResponseHandler(val call: HttpCallback) : Handler(Looper.getMainLooper()) {

    override fun handleMessage(msg: Message) {
        when(msg.what) {
            0 -> call.fail()
            1 -> {
                var response = msg.obj as String
                call.success(response)
            }
        }

    }
}