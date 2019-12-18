package com.further.x.weather


import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.further.foundation.BaseActivity
import com.further.foundation.util.ConstantUtil
import com.further.foundation.util.JsonUtil
import com.further.foundation.util.MobileUtil
import com.further.x.R
import com.further.x.city.CityChoiceActivity
import com.further.x.databinding.ActivityWeatherBinding
import com.further.x.http.HttpCallback
import com.further.x.http.HttpRequest
import com.further.x.http.ResponseHandler
import com.further.x.http.Url
import com.further.x.weather.vo.WeatherModel
import kotlinx.android.synthetic.main.activity_weather.*
import okhttp3.Cache


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

        recycler.layoutManager = LinearLayoutManager(this@WeatherActivity)
        recycler.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.set(0, 0, MobileUtil.dip2px(10), MobileUtil.dip2px(10))
            }
        })

        requestData("101020100")
    }

    private fun requestData(key : String) {
        if(key.isEmpty()) return
        startAnimation()
        val cache = Cache(this.externalCacheDir, 1024 * 100)
        HttpRequest.setC(cache)
        HttpRequest.get(Url.WEATHER + key, ResponseHandler(object : HttpCallback {
            override fun success(response: String) {

                stopAnimation()
                var weatherModel = JsonUtil.parseJson(response, WeatherModel::class.java)
                weatherModel?.let {
                    if (weatherModel.status != "200") {
                        Toast.makeText(this@WeatherActivity, weatherModel.message, Toast.LENGTH_SHORT).show()
                        return
                    }
                    binding.cityInfo = weatherModel.cityInfo
                    binding.firstDayWeather = weatherModel.data?.forecast?.get(0)
                    recycler.adapter = weatherModel.data?.forecast?.drop(1)?.let { WeatherItemAdapter(this@WeatherActivity, it) }

                }
            }

            override fun fail() {

                stopAnimation()
            }

        }))
    }

    fun onClickToCityChoice(view: View) {
        startActivityForResult(Intent(this, CityChoiceActivity::class.java), ConstantUtil.R_CODE)
    }

    override fun onActivityResult(requestCode : Int, resultCode : Int,  data : Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            var key = it.getStringExtra(ConstantUtil.CITY_KEY)
            requestData(key)
        }


    }

    fun onClickTransfer(view: View) {

        recycler.layoutManager = if (transfer)
            LinearLayoutManager(this@WeatherActivity)
        else GridLayoutManager(this@WeatherActivity, 2)
        recycler.adapter?.notifyDataSetChanged()
        transfer = !transfer
    }

    private fun startAnimation() {
        val anim = loading_img.background as AnimationDrawable
        if (!anim.isRunning) {
            anim.start()
        }
    }

    fun stopAnimation() {
        val anim = loading_img.background as AnimationDrawable
        if (anim.isRunning) {
            anim.setVisible(false, false)
        }
        loading_img.visibility = View.GONE
    }

}