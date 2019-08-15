package com.further.run.labzone.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.further.run.R;
import com.further.foundation.util.MobileUtil;
import com.further.run.util.RVAdapter;
import com.further.run.util.RVHolder;

import java.util.List;

/**
 * Created by Zion
 * 2019/8/5.
 */
public class ConfigurationIndicator extends FrameLayout {
    private RecyclerView recyclerView;
    private RVAdapter adapter;
    private int nowPosition;
    private OnClickListener onClickListener;

    private int checkColor;
    private int unCheckColor;
    private float textSize;
    private int indicatorBg;
    private int width;
    private int mScrollX = 0;

    public ConfigurationIndicator(@NonNull Context context) {
        super(context);

        init();
    }

    public ConfigurationIndicator(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public ConfigurationIndicator(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    private void initAttr(@NonNull Context context, @NonNull AttributeSet attrs) {
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ConfigurationIndicator);

    }

    private void init() {
        nowPosition = 0;
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        this.removeAllViews();
        this.addView(recyclerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MobileUtil.dip2px(45)));
//        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_ffffff));

        adapter = new RVAdapter<String>(getContext(), R.layout.c_indicator_item) {
            @Override
            public int getLayoutResId(String data) {
                return R.layout.c_indicator_item;
            }
            @Override
            public void convert(RVHolder holder, String data, int positon) {
                holder.getItemView().setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                holder.setText(R.id.item_name, "跟团游");
                ((TextView) holder.getView(R.id.item_name)).setTextSize(0, textSize);
//                holder.setTextColor(R.id.item_name, nowPosition == position ? checkColor : unCheckColor);
                holder.getView(R.id.item_indicator_bg).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_D30775));
            }
        };
//        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
//                nowPosition = position;
//                onClickListener.onClickListener(ConfigurationIndicator.this, view, position, mScrollX);
//            }
//        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mScrollX = recyclerView.computeHorizontalScrollOffset();
//                L.e("recyclerView.computeVerticalScrollOffset() " + recyclerView.computeHorizontalScrollOffset());
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    public void setData(List<String> datas) {

        //计算单个item的宽度，如果屏幕等分后依然可以容纳最长的宽度，则宽度等于等分，否则使用最长的宽度
        int maxLength = 0;
        for (String tString : datas) {
            maxLength = tString.length() > maxLength ? tString.length() : maxLength;
        }
        width = MobileUtil.getScreenWidth(getContext()) / datas.size();
        int singleWidth = maxLength * (int) textSize + MobileUtil.dip2px(10) * 2;
        if (width < singleWidth) {
            width = singleWidth;
        }
        if (adapter != null) adapter.replaceAll(datas);
    }

    public void changeTab(int position, int scrollX) {
        nowPosition = position;
        recyclerView.scrollBy(scrollX, 0);
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClickListener(ConfigurationIndicator indicator, View view, int position, int scorllX);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

