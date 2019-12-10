package com.further.x.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.further.x.BR
import com.further.x.R
import com.further.x.weather.vo.DayWeatherVo

/**
 * Created by Zion
 * 2019/12/10.
 */
class WeatherItemAdapter(val context: Context, private val mItems: List<DayWeatherVo>) : RecyclerView.Adapter<RecordItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordItemHolder {
        var binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.day_weather_display, parent, false)
        return RecordItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mItems.size
    }

    override fun onBindViewHolder(holder: RecordItemHolder, position: Int) {
        holder.binding.setVariable(BR.weatherInfo, mItems[position])
        holder.binding.executePendingBindings()
    }


}

class RecordItemHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {}