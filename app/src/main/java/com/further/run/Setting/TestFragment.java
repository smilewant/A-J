package com.further.run.Setting;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.further.run.R;

/**
 * Created by Zion
 * 2019/6/6.
 */
public class TestFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.activity_aidl, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String key = getArguments().getString("key");
        TextView ss = view.findViewById(R.id.start_service);
        ss.setText(key + "le");
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key", "第二页");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                TestFragment fragment = new TestFragment();
                ft.add(R.id.container, fragment, fragment.getClass().getName());
                ft.addToBackStack(fragment.getClass().getName());
                fragment.setArguments(bundle);
                ft.commit();
            }
        });
    }
}
