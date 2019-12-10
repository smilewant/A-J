package com.further.x.weather.vo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.further.x.R

/**
 * Created by Zion
 * 2019/12/4.
 */
class DayWeatherVo {
    var date: String = ""
    var high: String = ""
    var low: String = ""
    var ymd: String = ""
    var week: String = ""
    var sunrise: String = ""
    var sunset: String = ""
    var aqi: String = ""
    var fx: String = ""
    var fl: String = ""
    var type: String = ""
    var notice: String = ""

    fun weatherinfo1(): String {
        return fx + fl + "\n" + high + "/" + low + "\n  日出：" + sunrise + "日落 ：" + sunset
    }

    fun weatherInfo2(): String {
        return "$fx$fl  $high/$low"
    }

    fun weatherDate(): String {
        return "$ymd $week"
    }

    fun weatherIcon(context: Context): Drawable? {

        return when (type) {
            "晴" -> ContextCompat.getDrawable(context, R.drawable.weather_sunny)
            "阴",
            "多云" -> ContextCompat.getDrawable(context, R.drawable.weather_cloudy)
            "小雨" -> ContextCompat.getDrawable(context, R.drawable.weather_s_rain)
            else -> ContextCompat.getDrawable(context, R.drawable.weather_sunny)
        }
    }
    fun weatherItemIcon(context: Context): Drawable? {

        return when (type) {
            "晴" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_01)
            "阴",
            "多云" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_16)
            "小雨" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_19)
            else -> ContextCompat.getDrawable(context, R.drawable.weather_icon_01)
        }
    }

    fun weatherTextColor(context: Context): Int {
        return ContextCompat.getColor(context, when (type) {
            "晴" -> R.color.color_eda915
            "阴",
            "多云" -> R.color.color_eda915
            "小雨" -> R.color.color_eda915
            else -> R.color.color_eda915
        })
    }

    fun weatherBgColor(context: Context): Int {
        return ContextCompat.getColor(context, when (type) {
            "晴" -> R.color.color_79b5f7
            "阴",
            "多云" -> R.color.color_9db4d1
            "小雨" -> R.color.color_9db4d1
            else -> R.color.color_79b5f7
        })
    }

    fun weatherBg(context: Context): Drawable? {
        return when (type) {
            "晴" -> ContextCompat.getDrawable(context, R.drawable.sunny_bg)
            "阴",
            "多云" -> ContextCompat.getDrawable(context, R.drawable.winter_cloud_bg)
            "小雨" -> ContextCompat.getDrawable(context, R.drawable.rain_item_bg)
            else -> ContextCompat.getDrawable(context, R.drawable.sunny_bg)
        }

    }
}