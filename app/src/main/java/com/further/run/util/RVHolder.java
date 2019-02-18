package com.further.run.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Hukuan
 * 2018/5/15.
 */
public class RVHolder<T> extends RecyclerView.ViewHolder {
    private View itemView;
    public RVHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public View getItemView(){
        return itemView;
    }

    public void setText(int id, String text){
        TextView view  = itemView.findViewById(id);
        view.setText(text);
    }

    public <T extends View> T getView(int viewId) {
        T childView = itemView.findViewById(viewId);

        return (T) childView;
    }

}
