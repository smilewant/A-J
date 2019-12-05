package com.further.x.record

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.further.x.BR
import com.further.x.R
import com.further.x.record.vo.RecordItemVo


/**
 * Created by Zion
 * 2019/11/20.
 */
class RecordAdapter : RecyclerView.Adapter<RecordItemHolder> {
    private var mItems = ArrayList<RecordItemVo>()
    private var context : Context
    constructor( context : Context, datas: ArrayList<RecordItemVo> ) {
        this.mItems = datas
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordItemHolder {
        var binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.record_item, parent, false)
        return RecordItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mItems.size
    }

    override fun onBindViewHolder(holder: RecordItemHolder, position: Int) {
        holder.binding.setVariable(BR.item, mItems[position])
        holder.binding.executePendingBindings()
    }


}

class RecordItemHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {}
