package com.further.run.labzone.recyclerview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zion
 * 2019/6/19.
 */
public class SectionDecoration extends RecyclerView.ItemDecoration {
    private GroupListener mListener;
    private int mGroupHeight = 0;
    private boolean isAlignLeft = true;

    private SectionDecoration(GroupListener listener){
        this.mListener = listener;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        String groupId = getGroupName(pos);
        if (groupId == null) return;
        if (pos == 0 || isFirstGroup(pos)){
            outRect.top = mGroupHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount  = state.getItemCount();
        int childCount = parent.getChildCount();

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        String preGroupName;
        String currentGroupName = null;

        for (int i = 0; i < childCount; i++) {
            View view  = parent.getChildAt(i);
            int position =  parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(preGroupName, currentGroupName)){
                continue;
            }
            int viewBottom = view.getBottom();
            int top =  Math.max(mGroupHeight, view.getTop());
            if(position + 1 < itemCount) {
                String nextGroupName = getGroupName(position + 1);
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
            }
            View groupView = getGroupView(position);
            if (groupView == null) return;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mGroupHeight);
            groupView.setLayoutParams(layoutParams);
            groupView.setDrawingCacheEnabled(true);
            groupView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            groupView.layout(0,0,right, mGroupHeight);
            groupView.buildDrawingCache();
            Bitmap bt = groupView.getDrawingCache();
            int marginLeft = isAlignLeft? 0 : right - groupView.getMeasuredWidth();
            c.drawBitmap(bt, left+marginLeft, top - mGroupHeight, null);
        }
    }

    private boolean isFirstGroup(int pos) {
        if (pos == 0) return true;
        String preGroupId = getGroupName(pos - 1);
        String groupId = getGroupName(pos);
        return !TextUtils.equals(preGroupId, groupId);
    }

    private View getGroupView(int pos) {
        if (mListener != null) {
            return mListener.getGroupView(pos);
        }
        return null;
    }

    private String getGroupName(int pos) {
        if (mListener != null) {
            return mListener.getGroupName(pos);
        }
        return null;
    }

    public static class Builder {
        SectionDecoration mDecoration;
        private Builder(GroupListener listener) {
            mDecoration = new SectionDecoration(listener);
        }
        /**
         * 初始化 listener
         * @param listener
         * @return
         */
        public static Builder init(GroupListener listener) {
            return new Builder(listener);
        }
        /**
         * 设置Group高度
         * @param groutHeight 高度
         * @return this
         */
        public Builder setGroupHeight(int groutHeight) {
            mDecoration.mGroupHeight = groutHeight;
            return this;
        }
        /**
         * 是否靠左边
         * true 靠左边（默认）、false 靠右边
         * @param b b
         * @return  this
         */
        public Builder isAlignLeft(boolean b){
            mDecoration.isAlignLeft = b;
            return this;
        }
        public SectionDecoration build() {
            return mDecoration;
        }
    }

}
