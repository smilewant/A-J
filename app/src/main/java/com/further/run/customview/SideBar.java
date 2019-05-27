package com.further.run.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import com.further.run.R;
import com.further.run.log.LogUtil;

/**
 * Created by Zion
 * 2019/3/26.
 */
public class SideBar extends View {
    public static final int DEFAULT_COLOR = 0xFFd11f7f;
    private int m_nItemHeight = 29;
    private TextView mDialogText;
    private ArrayList<String> index;
    private PopupWindow mPopupWindow;
    private boolean needCurrent = true;
    private boolean needHot = true;
    private boolean needHistory = true;
    private int mTextColor = DEFAULT_COLOR;

    public SideBar(Context context) {
        super(context);
        init(context, null);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attrs) {

        index = new ArrayList<>();
        if (needCurrent) {
            index.add("当前");
        }
        if (needHistory) {
            index.add("历史");
        }
        if (needHot) {
            index.add("热门");
        }
        Collections.addAll(index, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        mDialogText = (TextView) LayoutInflater.from(context).inflate(R.layout.list_position, null);
        mDialogText.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.sidebar_bg));
        mDialogText.setTextColor(context.getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, height);
    }

    private int getViewHeight() {
        Rect rect = new Rect();
        String temp;
        int maxWidth = 0;
        for (int i = 0; i < index.size(); i++) {
            temp = index.get(i);

            if (maxWidth < rect.width()) {
                maxWidth = rect.width();
            }
        }
        return maxWidth;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int i = (int) event.getY();
        int idx = i / m_nItemHeight;
        if (idx >= index.size()) {
            idx = index.size() - 1;
        } else if (idx < 0) {
            idx = 0;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getAction() == MotionEvent.ACTION_MOVE) {

            String key = index.get(idx).trim();
            int position = 0;
            if ("当前".equals(key)) {
                position = 0;
            } else if ("历史".equals(key)) {
                position = 1;
            } else if ("热门".equals(key)) {
                position = 2;
            } else {
                if (!TextUtils.isEmpty(key)) {
                    char[] chars = key.toCharArray();
                    if (chars.length > 0) {
                        //根据分类列的索引号获得该序列的首个位置

                    }
                }
            }
            showPopup(index.get(idx) + "");
        } else {
            dismissPopup();
        }
        return true;
    }

    private void showPopup(String item) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(mDialogText, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        }
        mDialogText.setText(item);
//        if (mPopupWindow.isShowing()) {
//            mPopupWindow.update(); android 7.0 computeGravity有bug | 站点切换的时候，滑动导航栏字母提示位置显示错了
//        } else {
        mPopupWindow.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);
//        }
    }

    private void dismissPopup() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float widthCenter = 30 / 2;
        m_nItemHeight = getHeight() / 29;
        Paint paint = new Paint();
        paint.setColor(0xFF333333);
        paint.setTextSize(14);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < index.size(); i++) {
            String key = index.get(i);
            canvas.drawText(String.valueOf(index.get(i)), widthCenter, m_nItemHeight + (i * m_nItemHeight), paint);
        }
        this.invalidate();
        super.onDraw(canvas);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        dismissPopup();
        return super.onSaveInstanceState();
    }
}
