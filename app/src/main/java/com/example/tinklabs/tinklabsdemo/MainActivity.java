package com.example.tinklabs.tinklabsdemo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.example.tinklabs.tinklabsdemo.adapter.MainViewPageAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAB_SELECTED= "tab_selected";

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ViewPager viewPager = findViewById(R.id.viewpager);
        MainViewPageAdapter adapter = new MainViewPageAdapter(getSupportFragmentManager(), MainActivity.this);

        viewPager.setAdapter(adapter);

        mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(viewPager);
        if (savedInstanceState != null) {
            TabLayout.Tab selectedTab = mTabLayout.getTabAt(savedInstanceState.getInt(TAB_SELECTED, 0));
            if (selectedTab != null) {
                selectedTab.select();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAB_SELECTED, mTabLayout.getSelectedTabPosition());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).trimMemory(level);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
