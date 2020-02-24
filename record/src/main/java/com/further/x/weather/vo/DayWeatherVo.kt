package com.further.x.weather.vo

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.further.x.R

/**
 * Created by Zion
 * 2019/12/4.
 */
class DayWeatherVo() : Parcelable{
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

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        high = parcel.readString()
        low = parcel.readString()
        ymd = parcel.readString()
        week = parcel.readString()
        sunrise = parcel.readString()
        sunset = parcel.readString()
        aqi = parcel.readString()
        fx = parcel.readString()
        fl = parcel.readString()
        type = parcel.readString()
        notice = parcel.readString()
    }

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
            "晴" -> ContextCompat.getDrawable(context, R.drawable.sunny)
            "阴" -> ContextCompat.getDrawable(context, R.drawable.cloudy)
            "多云" -> ContextCompat.getDrawable(context, R.drawable.cloud)
            "小雨",
            "中雨" -> ContextCompat.getDrawable(context, R.drawable.rain)
            "雪",
            "小雪" -> ContextCompat.getDrawable(context, R.drawable.snow)
            else -> ContextCompat.getDrawable(context, R.drawable.sunny)
        }
    }

    fun weatherItemIcon(context: Context): Drawable? {

        return when (type) {
            "晴" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_01)
            "阴",
            "多云" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_16)
            "中雨",
            "小雨" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_19)
            "雪",
            "小雪" -> ContextCompat.getDrawable(context, R.drawable.weather_icon_31)
            else -> ContextCompat.getDrawable(context, R.drawable.weather_icon_01)
        }
    }

    fun weatherTextColor(context: Context): Int {
        return ContextCompat.getColor(context, when (type) {
            "晴" -> R.color.color_eda915
            "阴",
            "多云" -> R.color.color_eda915
            "中雨",
            "小雨" -> R.color.color_eda915
            "雪",
            "小雪" -> R.color.color_eda915
            else -> R.color.color_eda915
        })
    }

    fun weatherBgColor(context: Context): Int {
        return ContextCompat.getColor(context, when (type) {
            "晴" -> R.color.color_79b5f7
            "阴",
            "多云" -> R.color.color_9db4d1
            "中雨",
            "小雨" -> R.color.color_9db4d1
            "雪",
            "小雪" -> R.color.color_9db4d1
            else -> R.color.color_79b5f7
        })
    }

    fun weatherBg(context: Context): Drawable? {
        return when (type) {
            "晴" -> ContextCompat.getDrawable(context, R.drawable.sunny_bg)
            "阴",
            "多云" -> ContextCompat.getDrawable(context, R.drawable.winter_cloud_bg)
            "中雨",
            "小雨" -> ContextCompat.getDrawable(context, R.drawable.rain_item_bg_1)
            "雪",
            "小雪" -> ContextCompat.getDrawable(context, R.drawable.winter_sunny_bg)
            else -> ContextCompat.getDrawable(context, R.drawable.sunny_bg)
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(high)
        parcel.writeString(low)
        parcel.writeString(ymd)
        parcel.writeString(week)
        parcel.writeString(sunrise)
        parcel.writeString(sunset)
        parcel.writeString(aqi)
        parcel.writeString(fx)
        parcel.writeString(fl)
        parcel.writeString(type)
        parcel.writeString(notice)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DayWeatherVo> {
        override fun createFromParcel(parcel: Parcel): DayWeatherVo {
            return DayWeatherVo(parcel)
        }

        override fun newArray(size: Int): Array<DayWeatherVo?> {
            return arrayOfNulls(size)
        }
    }
}