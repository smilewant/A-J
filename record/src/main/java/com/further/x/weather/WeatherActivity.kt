package com.further.x.weather


import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.further.foundation.BaseActivity
import com.further.foundation.util.JsonUtil
import com.further.foundation.util.MobileUtil
import com.further.x.R
import com.further.x.databinding.ActivityWeatherBinding
import com.further.x.http.HttpCallback
import com.further.x.http.HttpRequest
import com.further.x.http.ResponseHandler
import com.further.x.http.Url
import com.further.x.weather.vo.WeatherModel
import kotlinx.android.synthetic.main.activity_weather.*


/**
 * Created by Zion
 * 2019/11/29.
 * https://blog.csdn.net/zsj777/article/details/102456224
 */
class WeatherActivity : BaseActivity() {
    private var transfer: Boolean = false
    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather)
        statusBarInit()
        dialogShow()
        HttpRequest.get(Url.WEATHER + "101020100", ResponseHandler(object : HttpCallback {
            override fun success(response: String) {
                dialogDismiss()
                var weatherModel = JsonUtil.parseJson(response, WeatherModel::class.java)
                binding.cityInfo = weatherModel.cityInfo
                binding.firstDayWeather = weatherModel.data?.forecast?.get(0)
                recycler.layoutManager = LinearLayoutManager(this@WeatherActivity)
                recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {

                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        outRect.set(0, 0, MobileUtil.dip2px(10), MobileUtil.dip2px(10))
                    }
                })
                recycler.adapter = weatherModel.data?.forecast?.drop(1)?.let { WeatherItemAdapter(this@WeatherActivity, it) }
            }

            override fun fail() {
                dialogDismiss()
            }

        }))
    }

    fun onClickTransfer(view:View) {
        recycler.layoutManager = if (transfer)
            LinearLayoutManager(this@WeatherActivity)
        else GridLayoutManager(this@WeatherActivity, 2)
        recycler.adapter?.notifyDataSetChanged()
        transfer = !transfer
    }

    override fun finish(){
        super.finish()
        overridePendingTransition(R.anim.dt_in_from_left, R.anim.dt_out_to_right)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_weather)
//
//        statusBarInit()
//        dialogShow()
//        HttpRequest.get(Url.WEATHER + "101020100", ResponseHandler(object : HttpCallback {
//            override fun success(response: String) {
//                LogUtil.e("response $response")
//                dialogDismiss()
//                var weatherModel = JsonUtil.parseJson(response, WeatherModel::class.java)
//                name.text = weatherModel.cityInfo?.city
//                var firstDayWeather = weatherModel.data?.forecast?.get(0)
//                firstDayWeather?.let {
//                    weather.text = "${firstDayWeather.fx} ${firstDayWeather.fl} \n ${firstDayWeather.high} / ${firstDayWeather.low} \n  日出：${firstDayWeather.sunrise} 日落 ：${firstDayWeather.sunset} "
//                    date.text = "${firstDayWeather.ymd} \t ${firstDayWeather.week}"
//                    current_weather.text = it.type
//                    current_weather.setTextColor(weatherTextColor(it.type))
//                    current_weather_icon.setBackgroundResource(weatherIcon(it.type))
//                    bg.setBackgroundResource(weatherBg(firstDayWeather.type))
//                }
//                name.setOnClickListener {
//                    recycler.layoutManager = if (transfer)
//                        LinearLayoutManager(this@WeatherActivity)
//                    else GridLayoutManager(this@WeatherActivity, 2)
//                    recycler.adapter?.notifyDataSetChanged()
//                    transfer = !transfer
//                }
//
//
//                recycler.layoutManager = LinearLayoutManager(this@WeatherActivity)
////
//                recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
//
//                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//                        super.getItemOffsets(outRect, view, parent, state)
//                        outRect.set(0, 0, MobileUtil.dip2px(10), MobileUtil.dip2px(10))
//                    }
//                })
//                recycler.adapter = object : RVAdapter<DayWeatherVo>(this@WeatherActivity, weatherModel.data?.forecast?.drop(1), R.layout.day_weather_display) {
//                    override fun convert(holder: RVHolder<*>?, data: DayWeatherVo?, positon: Int) {
//                        data?.let {
//                            holder?.setText(R.id.date, "${data.ymd} ${data.week}")
//                            holder?.setText(R.id.weather1, data.type)
//                            holder?.getView<TextView>(R.id.weather1)?.setTextColor(weatherTextColor(data.type))
//                            holder?.setText(R.id.wind, "${data.fx}${data.fl}  ${data.high}/${data.low}")
//                            holder?.getView<ImageView>(R.id.weather1_icon)?.let {
//                                it.setBackgroundResource(when (data.type) {
//                                    "晴" -> R.drawable.weather_icon_01
//                                    "阴",
//                                    "多云" -> R.drawable.weather_icon_16
//                                    "小雨" -> R.drawable.weather_icon_19
//                                    else -> R.drawable.weather_icon_01
//                                })
//                            }
//                            holder?.getView<ImageView>(R.id.item_bg)?.let {
//                                it.setBackgroundColor(ContextCompat.getColor(this@WeatherActivity,when(data.type){
//                                    "晴" -> R.color.color_79b5f7
//                                    "阴",
//                                    "多云" -> R.color.color_9db4d1
//                                    "小雨" -> R.color.color_9db4d1
//                                    else -> R.color.color_79b5f7
//                                }))
//                            }
//                        }
//                    }
//
//                }
//            }
//
//            override fun fail() {
//                dialogDismiss()
//            }
//
//        }))
//    }

    fun statusBarInit() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
//            val window = getWindow()
//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            val decorView = window.getDecorView()
//            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                if (percent > 200) {//和statusbar字体颜色相关
////                    option = option or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                } else {
////                    option = option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
////                }
//                option = option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                decorView.setSystemUiVisibility(option)
//                //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                window.setStatusBarColor(Color.argb(255,  0xff, 0xff, 0xff))
//            }

    }

}