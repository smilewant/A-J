package com.further.x.weather

import android.os.Bundle
import android.os.Handler
import com.further.foundation.BaseActivity
import com.further.foundation.util.JsonUtil
import com.further.foundation.util.LogUtil
import com.further.x.R
import com.further.x.http.HttpCallback
import com.further.x.http.HttpRequest
import com.further.x.http.ResponseHandler
import com.further.x.http.Url
import com.further.x.weather.vo.WeatherModel
import kotlinx.android.synthetic.main.activity_weather.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

/**
 * Created by Zion
 * 2019/11/29.
 * https://blog.csdn.net/zsj777/article/details/102456224
 */
class WeatherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)
        HttpRequest.get(Url.WEATHER + "101220101", ResponseHandler(object: HttpCallback{
            override fun success(response: String) {
                LogUtil.e("response $response")

                var weather = JsonUtil.parseJson(response, WeatherModel::class.java)
                name.text = weather.cityInfo?.city

            }

            override fun fail() {
            }

        }))
    }
}