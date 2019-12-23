package com.further.x.weather.vo

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Zion
 * 2019/12/4.
 */
class WeatherModel() : Parcelable{
    var message:String = ""
    var status:String = ""
    var date:String = ""
    var time:String = ""
    var cityInfo: CityInfoVo?= null
    var data:WeatherInfoVo ?= null

    constructor(parcel: Parcel) : this() {
        message = parcel.readString()
        status = parcel.readString()
        date = parcel.readString()
        time = parcel.readString()
        cityInfo = parcel.readParcelable(CityInfoVo::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeString(status)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeParcelable(cityInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherModel> {
        override fun createFromParcel(parcel: Parcel): WeatherModel {
            return WeatherModel(parcel)
        }

        override fun newArray(size: Int): Array<WeatherModel?> {
            return arrayOfNulls(size)
        }
    }
}