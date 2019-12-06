package com.further.x.weather


import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.further.foundation.BaseActivity
import com.further.foundation.adapter.RVAdapter
import com.further.foundation.adapter.RVHolder
import com.further.foundation.util.JsonUtil
import com.further.foundation.util.LogUtil
import com.further.foundation.util.MobileUtil
import com.further.x.R
import com.further.x.http.HttpCallback
import com.further.x.http.HttpRequest
import com.further.x.http.ResponseHandler
import com.further.x.http.Url
import com.further.x.weather.vo.DayWeatherVo
import com.further.x.weather.vo.WeatherModel
import kotlinx.android.synthetic.main.activity_weather.*

/**
 * Created by Zion
 * 2019/11/29.
 * https://blog.csdn.net/zsj777/article/details/102456224
 */
class WeatherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)
        HttpRequest.get(Url.WEATHER + "101020100", ResponseHandler(object : HttpCallback {
            override fun success(response: String) {
                LogUtil.e("response $response")

                var weatherModel = JsonUtil.parseJson(response, WeatherModel::class.java)
                name.text = weatherModel.cityInfo?.city
                var firstDayWeather = weatherModel.data?.forecast?.get(0)
                firstDayWeather?.let {
                    weather.text = "${firstDayWeather.fx} ${firstDayWeather.fl} \n ${firstDayWeather.high} / ${firstDayWeather.low} \n  日出：${firstDayWeather.sunrise} 日落 ：${firstDayWeather.sunset} \n${firstDayWeather.ymd} \t ${firstDayWeather.week}"
                }
                recycler.layoutManager = LinearLayoutManager(this@WeatherActivity)
                recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {

                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        outRect.set(0, 0, 0, MobileUtil.dip2px(10))
                    }
                })
                recycler.adapter = object : RVAdapter<DayWeatherVo>(this@WeatherActivity, weatherModel.data?.forecast, R.layout.day_weather_display) {
                    override fun convert(holder: RVHolder<*>?, data: DayWeatherVo?, positon: Int) {
                        data?.let {
                            holder?.setText(R.id.date, "${data.ymd} ${data.week}")
                            holder?.setText(R.id.weather1, data.type)
                            holder?.setText(R.id.wind, "${data.fx}${data.fl}  ${data.high}/${data.low}")
                        }
                    }

                }
            }

            override fun fail() {
            }

        }))
    }
}