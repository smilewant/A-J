package com.further.foundation.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 2018/5/15.
 */
public abstract class RVAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<T> datas = new ArrayList<>();
    private RVHolder rvHolder;
    protected Context mContext;
    private int resourceID;

    public RVAdapter(Context mContext, int resourceID){
        this.mContext = mContext;
        this.resourceID = resourceID;
    }

    public RVAdapter(Context mContext, List<T> datas, int resourceID){
        this.mContext = mContext;
        this.datas = datas;
        this.resourceID = resourceID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        rvHolder = new RVHolder(v);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((RVHolder)holder, datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutResId(datas.get(position));
    }

    public void replaceAll(List<T> elem) {
        datas.clear();
        datas.addAll(elem);
        notifyDataSetChanged();
    }


    public int getLayoutResId(T item){return resourceID;}

    public abstract void convert(RVHolder holder, T data, int positon);
}
