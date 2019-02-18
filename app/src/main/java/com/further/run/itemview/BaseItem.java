package com.further.run.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Hukuan
 * 2018/7/20.
 */
public abstract class BaseItem implements IBaseItem {

    protected Context mContext;

    public BaseItem(Context context){
        this.mContext = context;
    }

    protected View getView() {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        initView(view);
        return view;
    }

    abstract void initView(View view);
}
