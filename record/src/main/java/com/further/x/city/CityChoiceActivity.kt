package com.further.x.city

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
import com.further.foundation.BaseActivity
import com.further.foundation.GroupListener
import com.further.foundation.SectionDecoration
import com.further.foundation.adapter.RVAdapter
import com.further.foundation.adapter.RVHolder
import com.further.foundation.util.ConstantUtil
import com.further.foundation.util.LogUtil
import com.further.foundation.util.MobileUtil
import com.further.x.R
import com.further.x.room.CityData
import kotlinx.android.synthetic.main.activity_city_choice.*
import kotlin.concurrent.thread

/**
 * Created by Zion
 * 2019/12/12.
 */
class CityChoiceActivity : BaseActivity() {
    var citys: List<CityData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_choice)
        statusBarInit()

        val adapter = object : RVAdapter<CityData>(CityChoiceActivity@ this, R.layout.common_single_textview) {

            override fun convert(holder: RVHolder<*>?, data: CityData?, positon: Int) {
                var textView = holder?.itemView as TextView
                holder?.itemView?.setPadding(MobileUtil.dip2px(10))
                (holder?.itemView as TextView).text = data?.city
                holder.itemView.layoutParams.width = MATCH_PARENT
                textView.gravity = Gravity.CENTER
                when (data?.parent) {
                    "0" -> {
                        holder.itemView.setBackgroundResource(R.drawable.border_3f51b5_corner)
                        holder.itemView.layoutParams.height = MobileUtil.dip2px(40)
                    }
                    "1" -> holder.itemView.setBackgroundResource(R.drawable.border_79b5f7_corner)
                    "2" -> holder.itemView.setBackgroundResource(R.drawable.border_e4e4e4_corner)
                }
                holder.itemView.setOnClickListener {
                    data?.let {
                        if(data.parent == "0") return@setOnClickListener
                        var intent = Intent()
                        intent.putExtra(ConstantUtil.CITY_KEY, data.citykey)
                        setResult( ConstantUtil.R_CODE, intent)
                        finish()
                    }

                }
            }

        }
        var layoutManager = GridLayoutManager(this, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItem(position).parent == "2") 1 else 4
            }

        }

        city_choice_recycler.layoutManager = layoutManager
        city_choice_recycler.addItemDecoration(SectionDecoration.Builder.init(object : GroupListener {
            override fun getGroupName(pos: Int): String? {
                return citys?.let {
                    getParentName(pos,it)
                }
            }

            override fun getGroupView(pos: Int): View {
                var textView = TextView(this@CityChoiceActivity)
                textView.text = getGroupName(pos)
                textView.height = MobileUtil.dip2px(40)
                textView.width = MobileUtil.getScreenWidth(this@CityChoiceActivity) - MobileUtil.dip2px(30)
                textView.setBackgroundResource(R.drawable.border_3f51b5_corner)
                textView.gravity = Gravity.CENTER
                return textView
            }

        }).setGroupHeight(MobileUtil.dip2px(40)).build())
        city_choice_recycler.adapter = adapter

        thread {
            CityCatch.get(this)?.let {
                citys = it
                runOnUiThread {
                    adapter.replaceAll(it)
                }
            }
        }
    }

    fun getParentName(pos: Int, citys: List<CityData>): String {
        var name = ""

        for (i in 0 until pos + 1) {

            if (citys[i].parent == "0") {
                name = citys[i].city
            }

        }
        return name
    }

//    class Task : AsyncTask<String, Int, List<CityData>>() {
//        override fun doInBackground(vararg params: String?): List<CityData> {
//            return CityCatch.get(this@CityChoiceActivity)
//        }
//
//        override fun onPostExecute(result: List<CityData>?) {
//            super.onPostExecute(result)
//        }
//
//
//    }
}

