package com.further.run.labzone.hugeimagetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.further.run.R;
import com.further.run.log.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Hukuan
 * 2018/5/22.
 */
public class ImageThumbActivity extends AppCompatActivity {
    private ImageView innerView;
    private SHandler sHandler = new SHandler(this);
    private Bitmap bitmap;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huge_image);
        innerView = findViewById(R.id.inner_view);
        getExsitImage();

    }

    private static class SHandler extends Handler {
        private final WeakReference<ImageThumbActivity> mActivity;

        public SHandler(ImageThumbActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivity.get() != null) {
                mActivity.get().dispatch(msg);
            }
        }
    }

    private void dispatch(Message msg) {


    }

    private void getExsitImage() {
        File imageDir = new File(getExternalCacheDir().getAbsolutePath(), "image");
        if (!imageDir.isDirectory()) {
            imageDir.mkdir();
        }
        File file = new File(imageDir.getAbsolutePath(), "poster_thumb.jpg");
        if (file.exists()) {
            LogUtil.e("File2byte : " + File2byte(file.getAbsolutePath()).length);
            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inTempStorage = new byte[1024];
            options.inJustDecodeBounds = true;
              BitmapFactory.decodeFile(file.getAbsolutePath(), options);//转化为bitmap
//            final int minSideLength = Math.min(width, height);
//            options.inSampleSize = getFitInSampleSize(options, minSideLength,
//                    width * height);         //这里一定要将其设置回false，因为之前我们将其设置成了true
            options.inInputShareable = true;
            options.inPurgeable = true;
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            Bitmap bitmap_ = BitmapFactory.decodeFile(file.getAbsolutePath(), options);//转化为bitmap
            LogUtil.e("result bitmap_ : " + bitmap_.getByteCount());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            bitmap_.compress(Bitmap.CompressFormat.WEBP, 100, output);
            byte[] result = output.toByteArray();//转化为字节码
            LogUtil.e("result : " + result.length);
        }
    }

    public static int getFitInSampleSize(int reqWidth, int reqHeight, BitmapFactory.Options options) {
        int inSampleSize = 1;
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            int widthRatio = Math.round((float) options.outWidth / (float) reqWidth);
            int heightRatio = Math.round((float) options.outHeight / (float) reqHeight);
            inSampleSize = Math.min(widthRatio, heightRatio);
        }
        return inSampleSize;
    }

    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[6*1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            buffer = bos.toByteArray();

            Bitmap bm = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
            LogUtil.e("result bm : " + bm.getByteCount());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }


    private static Bitmap compressImage(Bitmap image, int size, int options) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > size) {
            options /= 2;// 每次都减少10
            baos.reset();// 重置baos即清空baos
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    private void compressBitmap(Bitmap bitmap) {
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
