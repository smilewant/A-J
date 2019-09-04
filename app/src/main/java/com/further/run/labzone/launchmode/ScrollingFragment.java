package com.further.run.labzone.launchmode;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.further.run.R;

/**
 * Created by Hukuan
 * 2019/2/14.
 */
public class ScrollingFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.activity_aidl, container, false);
    }
}
