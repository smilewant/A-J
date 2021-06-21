package com.further.x.parse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * create by FangQi on 2:19 PM, Tue, 2020/9/8
 * qi.fang@aihuishou.com
 *
 * 描述：--
 **/
@Serializable
data class IosCrashLogResp(
    val crashCount: Int = 0,
    val crashLogs: List<CrashLog> = listOf(),
    val resetCount: Int = 0,
    val udid: String = ""
)

@Serializable
data class CrashLog(
        val content: String = "",
        val count: Int = 0,
        val logTime: String = ""
)