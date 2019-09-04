package com.further.run.Setting;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.further.run.R;

/**
 * Created by Zion
 * 2019/4/23.
 */
public class FragmentManagerActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        TestFragment fragment = new TestFragment();
        ft.add(R.id.container, fragment, fragment.getClass().getName());
        ft.addToBackStack(fragment.getClass().getName());
        fragment.setArguments(bundle);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 1) {
//            FragmentManager.BackStackEntry entry = fragmentManager
//                    .getBackStackEntryAt(count - 1);
//
//            fragmentManager.beginTransaction().remove(
//                    (fragmentManager.findFragmentByTag(entry.getName())));
//            fragmentManager.beginTransaction().commit();
//            fragmentManager.popBackStack();
            super.onBackPressed();
        } else {
            finish();
        }
    }
}
