package com.further.run.labzone.optimize;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：Hukuan
 * 创建时间：2017/2/28 11:09
 * 修改人：Hukuan
 * 修改时间：2017/2/28 11:09
 * 修改备注：
 */
public class HolidayDetailStructuredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<HolidayDetailStructuredItemVo> datas = new ArrayList<>();

    private HolidayDetailStructuredHolder holidayDetailStructuredHolder;

    public HolidayDetailStructuredAdapter(Context context){
        holidayDetailStructuredHolder = new HolidayDetailStructuredHolder(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        return holidayDetailStructuredHolder.myDetailStructuredHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holidayDetailStructuredHolder.bindView(holder, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void replaceData(List<HolidayDetailStructuredItemVo> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


}

