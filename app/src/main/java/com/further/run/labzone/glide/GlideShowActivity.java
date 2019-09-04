package com.further.run.labzone.glide;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.further.run.R;
import com.further.run.customview.BaseDialog;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hukuan
 * 2018/6/20.
 */
public class GlideShowActivity extends AppCompatActivity {
    private BaseDialog salesPromotionDialog;
    private TextView clickTV;
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_show);

        final ImageView imgIV = findViewById(R.id.img);
        clickTV = findViewById(R.id.click);
        clickTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "http://pic.lvmama.com/uploads/pc/place2/2018-06-25/26c913d0-13c6-4cdf-a403-66c941e1c842_720_.jpg";
                String url = "https://pics.lvjs.com.cn/pics/lego/back/201906/1559699658963.jpg";
                Glide.with(GlideShowActivity.this)
                        .load(url)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher_round)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgIV);
//                request();
//                showSharePopWindow();
            }
        });

         salesPromotionDialog = new BaseDialog();
    }

    private void request(){
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().url("http://www.baidu.com").build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("httpClient onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtil.e("httpClient onResponse " + response.toString());

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("TAGLIST", (Serializable) productTagList);
//                salesPromotionDialog.setArguments(bundle);
                salesPromotionDialog.show(getSupportFragmentManager(), "sales");
            }
        });


    }

    protected void showSharePopWindow(){
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(MobileUtil.dip2px(160));
        View layout = LayoutInflater.from(this).inflate(R.layout.route_share_popup_window, null);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(layout);
//                popupWindow.setAnimationStyle(R.style.popupAnimation);
        // 需要打开
        popupWindow.showAsDropDown(clickTV, 100, MobileUtil.dip2px(2));
    }


}
