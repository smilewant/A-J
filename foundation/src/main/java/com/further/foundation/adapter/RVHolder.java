package com.further.foundation.adapter;

import androidx.recyclerview.widget.RecyclerView;
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
        TextView view  = getView(id);
        view.setText(text);
    }

    public <T extends View> T getView(int viewId) {
        T childView = itemView.findViewById(viewId);

        return (T) childView;
    }

}
