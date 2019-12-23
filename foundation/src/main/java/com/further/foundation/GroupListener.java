package com.further.foundation;

import android.view.View;

/**
 * Created by Zion
 * 2019/6/19.
 */
public interface GroupListener {
    String getGroupName(int pos);
    View getGroupView(int pos);
    View getFloatView();
}
