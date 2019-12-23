package com.further.x.weather.vo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Zion
 * 2019/12/4.
 */
class CityInfoVo() : Parcelable {
    var city: String = ""
    var citykey: String = ""
    var parent: String = ""
    var updateTime: String = ""

    constructor(parcel: Parcel) : this() {
        city = parcel.readString()
        citykey = parcel.readString()
        parent = parcel.readString()
        updateTime = parcel.readString()
    }

    fun getShowCity() : String{
        return "$parent $city"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(citykey)
        parcel.writeString(parent)
        parcel.writeString(updateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityInfoVo> {
        override fun createFromParcel(parcel: Parcel): CityInfoVo {
            return CityInfoVo(parcel)
        }

        override fun newArray(size: Int): Array<CityInfoVo?> {
            return arrayOfNulls(size)
        }
    }
}