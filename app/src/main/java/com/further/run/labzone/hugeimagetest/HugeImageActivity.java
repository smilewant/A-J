package com.further.run.labzone.hugeimagetest;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.further.run.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hukuan
 * 2018/5/22.
 */
public class HugeImageActivity extends AppCompatActivity {
    private ImageView innerView;
    private SHandler sHandler = new SHandler(this);
    private Bitmap bitmap;
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huge_image);
         innerView = findViewById(R.id.inner_view);
        new Thread(new Runnable() {
            @Override
            public void run() {
                returnBitMap("http://pic.lvmama.com/uploads/pc/place2/2018-04-17/a426b896-453d-4197-a7e7-1186b3625849_960_.jpg");
//                returnBitMap("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527590754&di=d84c223df56d61befe38e6525891fc8b&imgtype=jpg&er=1&src=http%3A%2F%2Fsi1.go2yd.com%2Fget-image%2F0GP0zAfDI0G");
            }
        }).start();
    }

    private static class SHandler extends Handler {
        private final WeakReference<HugeImageActivity> mActivity;

        public SHandler(HugeImageActivity activity) {
            mActivity = new WeakReference<>(activity);
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() != null) {
                mActivity.get().dispatch(msg);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void dispatch(Message msg){


        ViewGroup.LayoutParams layoutParams = innerView.getLayoutParams();
        layoutParams.width = MobileUtil.getScreenWidth(this);
        layoutParams.height = bitmap.getHeight();
        innerView.setLayoutParams(layoutParams);

        innerView.setImageBitmap(bitmap);

    }

    public Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
//        bitmap = BitmapFactory.decodeFile(url, options);
//        options.inSampleSize = calculateInSampleSize(options, MobileUtil.getScreenWidth(this), MobileUtil.getScreenHeight(this));
        options.inSampleSize= 2;
//        options.outWidth = MobileUtil.getScreenWidth(this);
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
//            conn.connect();
            InputStream is = conn.getInputStream();
//            options.inSampleSize = calculateInSampleSize(options, bitmap.getWidth(), bitmap.getHeight());
            bitmap = BitmapFactory.decodeStream(is, null, options);
            LogUtil.e("options.outWidth " + MobileUtil.getScreenWidth(this) + " " + bitmap.getWidth());
            LogUtil.e("options.outWidth " + MobileUtil.getScreenHeight(this) + " " + bitmap.getHeight());
//            bitmap = BitmapFactory.decodeFile(url, options);
//            bitmap = compressImage(bitmap, 700, 80);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        sHandler.sendEmptyMessageDelayed(0, 1000);

        return bitmap;
    }

    private static Bitmap compressImage(Bitmap image,int size,int options) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > size) {
            options -= 10;// 每次都减少10
            if (options < 0)break;
            baos.reset();// 重置baos即清空baos
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    private void compressBitmap(Bitmap bitmap){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = calculateInSampleSize(options, bitmap.getWidth(), bitmap.getHeight());
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;


        if (height > reqHeight || width > reqWidth) {
            if (height > width) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public void onDestroy() {
        super.onDestroy();
        if (bitmap != null) bitmap.recycle();
    }
}
