package com.further.run.labzone.launchmode;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.further.run.R;

import io.flutter.facade.Flutter;
import io.flutter.facade.FlutterFragment;

public class ScrollingActivity extends AppCompatActivity {
    ScrollingFragment scrollingFragment;
    FlutterFragment flutterFragment;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                View flutterView = Flutter.createView(
//                        ScrollingActivity.this,
//                        getLifecycle(),
//                        "route1"
//                );
//                FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                addContentView(flutterView, layout);
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

                if (scrollingFragment == null) {
                    scrollingFragment = new ScrollingFragment();

                }
                if (flutterFragment == null) {
                    flutterFragment = Flutter.createFragment("", 2);
                }

                if (currentFragment == null) {
                    currentFragment = scrollingFragment;
                } else {
                    tx.hide(currentFragment);
                    currentFragment = currentFragment == scrollingFragment? flutterFragment : scrollingFragment;
                }

                if (currentFragment.isAdded()){
                    tx.show(currentFragment);
                } else {
                    tx.add(R.id.flutter_layout1, currentFragment);
                }

                tx.commit();
            }
        });

//        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//        tx.replace(R.id.flutter_layout1, Flutter.createFragment("", 2));
//        tx.commit();
    }
}