package com.further.x.weather.vo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Zion
 * 2019/12/4.
 */
class WeatherInfoVo() : Parcelable{
    var shidu:String = ""
    var pm25:String = ""
    var pm10:String = ""
    var quality:String = ""
    var wendu:String = ""
    var ganmao:String = ""
    var forecast:List<DayWeatherVo> ?= null
    var yesterday:DayWeatherVo ?= null

    constructor(parcel: Parcel) : this() {
        shidu = parcel.readString()
        pm25 = parcel.readString()
        pm10 = parcel.readString()
        quality = parcel.readString()
        wendu = parcel.readString()
        ganmao = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(shidu)
        parcel.writeString(pm25)
        parcel.writeString(pm10)
        parcel.writeString(quality)
        parcel.writeString(wendu)
        parcel.writeString(ganmao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherInfoVo> {
        override fun createFromParcel(parcel: Parcel): WeatherInfoVo {
            return WeatherInfoVo(parcel)
        }

        override fun newArray(size: Int): Array<WeatherInfoVo?> {
            return arrayOfNulls(size)
        }
    }


}