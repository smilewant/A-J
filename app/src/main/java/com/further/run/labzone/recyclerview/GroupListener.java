package com.further.run.labzone.recyclerview;

import android.view.View;

/**
 * Created by Zion
 * 2019/6/19.
 */
public interface GroupListener {
    String getGroupName(int pos);
    View getGroupView(int pos);
}
