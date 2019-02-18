package com.further.run.itemview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.further.run.R;

/**
 * Created by Hukuan
 * 2018/7/20.
 */
public class HolidayCalendarItem extends BaseItem {
    private RecyclerView mRecylerView;

    public HolidayCalendarItem(Context context) {
        super(context);
    }

    @Override
    void initView(View view) {

    }


    @Override
    public int getLayoutId() {
        return R.layout.holiday_calendar_item;
    }


}
