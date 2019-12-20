package com.further.x.weather.vo

/**
 * Created by Zion
 * 2019/12/4.
 */
class CityInfoVo {
    var city: String = ""
    var citykey: String = ""
    var parent: String = ""
    var updateTime: String = ""

    fun getShowCity() : String{
        return parent + ' ' + city
    }
}