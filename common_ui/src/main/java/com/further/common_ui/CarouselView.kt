package com.further.common_ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ViewFlipper

class CarouselView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {


    private val view: View = LayoutInflater.from(context).inflate(
            R.layout.main_carousel_layout,
            null
    )

    init {
        addView(view)

    }

    fun setData(key: String, value: String) {
        val viewFlipper: ViewFlipper = view.findViewById(R.id.carousel_item_flipper)
        val item = LayoutInflater.from(context).inflate(R.layout.main_carousel_item, null);
        viewFlipper.addView(item)
        val item1 = LayoutInflater.from(context).inflate(R.layout.main_carousel_item, null);
        viewFlipper.addView(item1)
    }
}