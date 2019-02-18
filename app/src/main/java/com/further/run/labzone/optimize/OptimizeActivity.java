package com.further.run.labzone.optimize;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.further.run.R;
import com.further.run.log.LogUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 2018/6/26.
 */
public class OptimizeActivity extends AppCompatActivity {
    private TextView leftTxt, rightTxt;
    private OwnRecylerview recyclerView;
    private HolidayDetailStructuredAdapter adapter;
    private LinearLayout layout;

    private List<HolidayDetailStructuredItemVo> vos;
    private CHandler cHandler = new CHandler(this);
    private DHandler dHandler = new DHandler(this);
    boolean poi = false;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);


        leftTxt = findViewById(R.id.left_txt);
        rightTxt = findViewById(R.id.right_txt);
        layout = findViewById(R.id.linear_layout);
        leftTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i++ < 20) {

                            poi = !poi;
                            cHandler.sendEmptyMessage(poi ? 1 : 0);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        });
        rightTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(1);
                addItem();
                cHandler.sendEmptyMessageDelayed(0, 1000);
                cHandler.sendEmptyMessage(1);
                dHandler.sendEmptyMessageDelayed(2, 500);
                dHandler.sendEmptyMessage(3);
            }
        });

        recyclerView = findViewById(R.id.optimize_recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HolidayDetailStructuredAdapter(this);
        recyclerView.setAdapter(adapter);

        vos = new ArrayList<>();
    }

    private void show(int position) {
        LogUtil.e("postion " + position);
        vos.clear();
        for (int i = 0; i < 2; i++) {
            HolidayDetailStructuredItemVo vo = new HolidayDetailStructuredItemVo();
            vo.type = "this" + i + position;
            vo.name = "this" + i + position;
            vo.nameSupply = "this" + i + position;
            vo.attach = "this" + i + position;
            vo.desc = "this" + i + position;
            vo.logicName = "this" + i + position;
            vo.imgUrls = null;
            vo.id = "this" + i + position;
            vo.nameTxt = "this" + i + position;
            vos.add(vo);
        }
//        adapter.replaceData(vos);
        addItem();

    }

    private static class CHandler extends Handler {
        private final WeakReference<OptimizeActivity> mFragment;

        public CHandler(OptimizeActivity fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mFragment.get().show(msg.what);
        }
    }

    private static class DHandler extends Handler {
        private final WeakReference<OptimizeActivity> mFragment;

        public DHandler(OptimizeActivity fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mFragment.get().show(msg.what);
        }
    }

    private void addItem() {
//        layout.invalidate();
        layout.removeAllViews();
        for (HolidayDetailStructuredItemVo vo : vos) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.holiday_detail_structured_holder, layout, true);
            ImageView icon = itemView.findViewById(R.id.icon);
            TextView name = itemView.findViewById(R.id.name);
            TextView nameSupply = itemView.findViewById(R.id.name_supply);
            TextView attach = itemView.findViewById(R.id.attach);
            TextView desc = itemView.findViewById(R.id.desc);
            ImageView imgBig = itemView.findViewById(R.id.img_big);
            ImageView img1 = itemView.findViewById(R.id.img1);
            ImageView img2 = itemView.findViewById(R.id.img2);
            ImageView img3 = itemView.findViewById(R.id.img3);
            ImageView img4 = itemView.findViewById(R.id.img4);
            TextView txtBig = itemView.findViewById(R.id.txt_big);
            TextView txt1 = itemView.findViewById(R.id.txt1);
            TextView txt2 = itemView.findViewById(R.id.txt2);
            TextView txt3 = itemView.findViewById(R.id.txt3);
            TextView txt4 = itemView.findViewById(R.id.txt4);

            if ("HOTEL".equals(vo.type)) {
                icon.setBackgroundResource(R.drawable.ic_launcher_foreground);
            } else if ("VEHICLE".equals(vo.type)) {
                icon.setBackgroundResource(R.drawable.ic_launcher_foreground);
            } else if ("SCENIC".equals(vo.type)) {
                icon.setBackgroundResource(R.drawable.ic_launcher_foreground);
            }
            icon.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(vo.logicName)) {
                icon.setVisibility(View.INVISIBLE);
            }
            if (!TextUtils.isEmpty(vo.name)) {
                name.setText(vo.name);
                name.setVisibility(View.VISIBLE);
            } else {
                name.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(vo.nameSupply)) {
                nameSupply.setText(vo.nameSupply);
                nameSupply.setVisibility(View.VISIBLE);
            } else {
                nameSupply.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(vo.attach)) {
                attach.setText(vo.attach);
                attach.setVisibility(View.VISIBLE);
            } else {
                attach.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(vo.desc)) {
                desc.setText(vo.desc.trim());
                name.setVisibility(View.VISIBLE);
            } else {
                desc.setVisibility(View.GONE);
            }
//            itemView.invalidate();
//            layout.addView(itemView);
        }
    }
}
