package com.further.run.labzone.optimize;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.further.run.log.LogUtil;

/**
 * Created by Hukuan
 * 2018/6/26.
 */
public class OwnRecylerview extends RecyclerView {
    public OwnRecylerview(Context context) {
        super(context);
    }

    public OwnRecylerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OwnRecylerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int widthSpec, int heightSpec){
        LogUtil.d("INNER onMeasure");
        super.onMeasure(widthSpec, heightSpec);
    }

    public void onDraw(Canvas c){
        LogUtil.d("INNER onDraw");
        super.onDraw(c);
    }
}
