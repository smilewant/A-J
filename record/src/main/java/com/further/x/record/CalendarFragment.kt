package com.further.x.record

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.further.foundation.adapter.RVAdapter
import com.further.foundation.adapter.RVHolder
import com.further.foundation.util.MobileUtil
import com.further.x.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Zion
 * 2019/12/20.
 */
class CalendarFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle( STYLE_NO_FRAME, R.style.NiceDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, null);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar_recycler.layoutManager = GridLayoutManager(activity, 7)
        calendar_recycler.adapter = object : RVAdapter<String>(activity, getData(), R.layout.common_single_textview) {
            override fun convert(holder: RVHolder<*>?, data: String?, positon: Int) {
                var textView = holder?.itemView as TextView
                textView.text = data
                textView.gravity = Gravity.CENTER
                textView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                textView.layoutParams.height = MobileUtil.getScreenHeight(activity) / 2 / 9
                when(data)  {
                    "日",
                        "六" -> textView.setTextColor(ContextCompat.getColor(mContext, R.color.color_fe3e71))
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        window?.let {
            var params = window.attributes
            params.height = MobileUtil.getScreenHeight(activity) * 50 / 100
            params.width = MobileUtil.getScreenWidth(activity)
            params.gravity = Gravity.BOTTOM
            window.attributes = params
        }
    }

    private fun getData(): ArrayList<String> {
        var cal = Calendar.getInstance()
        var monthDays = ArrayList<String>()
        val weekday = arrayListOf("日", "一", "二", "三", "四", "五", "六")
        monthDays.addAll(weekday)
        for (i in 0 until  cal.firstDayOfWeek - 1) {
            monthDays.add("")
        }
        for (j in 0 until cal.getActualMaximum(Calendar.DATE)) {
            monthDays.add((j + 1).toString())
        }

        return monthDays
    }
}