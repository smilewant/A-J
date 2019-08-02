package com.further.common_ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion
 * 2019/7/23.
 * 可变换的指示器
 */
public class ConfigurationIndicator extends FrameLayout {

    public ConfigurationIndicator(@NonNull Context context) {
        super(context);
        init();
    }

    public ConfigurationIndicator(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConfigurationIndicator(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(new CIndicatorAdapter(list));
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        this.addView(recyclerView);
        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_fd3c71));
    }

    class CIndicatorAdapter extends RecyclerView.Adapter {
        List<String> itemList;

        CIndicatorAdapter(List<String> list) {
            if (itemList == null)
                itemList = new ArrayList<>();
            itemList.clear();
            itemList.addAll(list);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.c_indicator_item, null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Holder holder = (Holder) viewHolder;
            viewHolder.itemView.setLayoutParams(new ViewGroup.LayoutParams(200, 100));
            holder.mIndicatorView.setVisibility(GONE);
            holder.mName.setText(itemList.get(i));
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView mName;
        private View mIndicatorView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.item_name);
            mIndicatorView = itemView.findViewById(R.id.item_indicator_bg);
        }

    }
}
