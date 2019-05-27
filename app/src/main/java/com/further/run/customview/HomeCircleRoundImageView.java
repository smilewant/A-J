package com.further.run.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import com.further.run.R;

/**
 * Created by Zion
 * 2019/4/23.
 */
public class HomeCircleRoundImageView extends RatioImageView {
    private Path path = new Path();
    private float topRadius, bottomRadius;
    private float maxRadius;

    public HomeCircleRoundImageView(Context context) {
        this(context, null);
    }

    public HomeCircleRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public HomeCircleRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HomeCircleRoundImageView);
        try {
            topRadius = ta.getDimension(R.styleable.HomeCircleRoundImageView_hcriRadiusTop, 0f);
            bottomRadius = ta.getDimension(R.styleable.HomeCircleRoundImageView_hcriRadiusBottom, 0f);
        } finally {
            ta.recycle();
        }
        maxRadius = Math.max(topRadius, bottomRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width > maxRadius && height > maxRadius) {
            path.moveTo(topRadius, 0);
            path.lineTo(width - topRadius, 0);
            path.quadTo(width, 0, width, topRadius);
            path.lineTo(width, height - bottomRadius);
            path.quadTo(width, height, width - bottomRadius, height);
            path.lineTo(bottomRadius, height);
            path.quadTo(0, height, 0, height - bottomRadius);
            path.lineTo(0, topRadius);
            path.quadTo(0, 0, topRadius, 0);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
