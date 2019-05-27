package com.further.run.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.further.run.R;

/**
 * Created by Zion
 * 2019/4/23.
 */
public class RatioImageView extends AppCompatImageView {
    private int originalWidth;
    private int originalHeight;

    public RatioImageView(Context context) {
        this(context, (AttributeSet)null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);

        try {
            this.originalWidth = ta.getInt(R.styleable.RatioImageView_originWidth, 0);
            this.originalHeight = ta.getInt(R.styleable.RatioImageView_originHeight, 0);
        } finally {
            ta.recycle();
        }

    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.originalWidth > 0 && this.originalHeight > 0) {
            float ratio = (float)this.originalWidth / (float)this.originalHeight;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (width > 0) {
                height = (int)((float)width / ratio);
            }

            this.setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
