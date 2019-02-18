package com.further.run.labzone.optimize;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.further.run.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：Hukuan
 * 创建时间：2017/2/28 10:49
 * 修改人：Hukuan
 * 修改时间：2017/2/28 10:49
 * 修改备注：
 */
public class HolidayDetailStructuredHolder {
    private Context mContext;
    private List<HolidayDetailStructuredItemVo> voList;

    public HolidayDetailStructuredHolder(Context mContext) {
        this.mContext = mContext;
        voList = new ArrayList<>();
    }

    public RecyclerView.ViewHolder myDetailStructuredHolder(ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.holiday_detail_structured_holder, parent, false);
        return new HolidayDetailStructuredHolder.IndexVH(v);
    }

    public void bindView(RecyclerView.ViewHolder holder, HolidayDetailStructuredItemVo item) {

        HolidayDetailStructuredHolder.IndexVH vh = (HolidayDetailStructuredHolder.IndexVH) holder;
        if (!TextUtils.isEmpty(item.logicName)) {
            vh.icon.setVisibility(View.INVISIBLE);
        }
        if (!TextUtils.isEmpty(item.name)) {
            vh.name.setText(item.name);
            vh.name.setVisibility(View.VISIBLE);
        } else {
            vh.name.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.nameSupply)) {
            vh.nameSupply.setText(item.nameSupply);
            vh.nameSupply.setVisibility(View.VISIBLE);
        } else {
            vh.nameSupply.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.attach)) {
            vh.attach.setText(item.attach);
            vh.attach.setVisibility(View.VISIBLE);
        } else {
            vh.attach.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.desc)) {
            vh.desc.setText(item.desc.trim());
            vh.name.setVisibility(View.VISIBLE);
        } else {
            vh.desc.setVisibility(View.GONE);
        }

        vh.imgBig.setVisibility(View.GONE);
        vh.img1.setVisibility(View.GONE);
        vh.img2.setVisibility(View.GONE);
        vh.img3.setVisibility(View.GONE);
        vh.img4.setVisibility(View.GONE);
        vh.txtBig.setVisibility(View.GONE);
        vh.txt1.setVisibility(View.GONE);
        vh.txt2.setVisibility(View.GONE);
        vh.txt3.setVisibility(View.GONE);
        vh.txt4.setVisibility(View.GONE);
        if (item.imgUrls != null) {
            vh.txtBig.setText(item.nameTxt);
            vh.txt1.setText(item.nameTxt);
            vh.txt2.setText(item.nameTxt);
            vh.txt3.setText(item.nameTxt);
            vh.txt4.setText(item.nameTxt);

            switch (item.imgUrls.size()) {
                case 1:
                    vh.imgBig.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(0)).placeholder(R.drawable.ic_launcher_foreground).into(vh.imgBig);
                    vh.txtBig.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    vh.img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(0)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img1);
                    vh.img2.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(1)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img2);
                    vh.txt1.setVisibility(View.VISIBLE);
                    vh.txt2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    vh.imgBig.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(0)).placeholder(R.drawable.ic_launcher_foreground).into(vh.imgBig);
                    vh.img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(1)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img1);
                    vh.img2.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(2)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img2);
                    vh.txtBig.setVisibility(View.VISIBLE);
                    vh.txt1.setVisibility(View.VISIBLE);
                    vh.txt2.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    vh.img1.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(0)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img1);
                    vh.img2.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(1)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img2);
                    vh.img3.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(2)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img3);
                    vh.img4.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(item.imgUrls.get(3)).placeholder(R.drawable.ic_launcher_foreground).into(vh.img4);
                    vh.txt1.setVisibility(View.VISIBLE);
                    vh.txt2.setVisibility(View.VISIBLE);
                    vh.txt3.setVisibility(View.VISIBLE);
                    vh.txt4.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }


    private String parseTime(String time) {
        String parseTime = "";
        if (time.indexOf(":") > -1) {
            String[] temp = time.split(":");
            if (temp.length == 1) {
                parseTime = temp[0] + "小时";
            } else if (temp.length == 2) {
                if (!TextUtils.isEmpty(temp[0]) && !"0".equals(temp[0])) {
                    parseTime += temp[0] + "小时";
                }
                if (!TextUtils.isEmpty(temp[1]) && !"0".equals(temp[1])) {
                    parseTime += temp[1] + "分钟";
                }
            }
            return parseTime;
        } else {
            return time;
        }
    }


    private class IndexVH extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView nameSupply;
        TextView attach;
        TextView desc;
        ImageView imgBig;
        ImageView img1;
        ImageView img2;
        ImageView img3;
        ImageView img4;
        TextView txtBig;
        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;

        public IndexVH(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            nameSupply = itemView.findViewById(R.id.name_supply);
            attach = itemView.findViewById(R.id.attach);
            desc = itemView.findViewById(R.id.desc);
            imgBig = itemView.findViewById(R.id.img_big);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            txtBig = itemView.findViewById(R.id.txt_big);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
            txt4 = itemView.findViewById(R.id.txt4);
        }
    }

}
