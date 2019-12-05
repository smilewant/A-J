package com.further.x.weather.vo

/**
 * Created by Zion
 * 2019/12/4.
 */
class WeatherInfoVo {
    var shidu:String = ""
    var pm25:String = ""
    var pm10:String = ""
    var quality:String = ""
    var wendu:String = ""
    var ganmao:String = ""
    var forecast:List<DayWeatherVo> ?= null
    var yesterday:DayWeatherVo ?= null


}