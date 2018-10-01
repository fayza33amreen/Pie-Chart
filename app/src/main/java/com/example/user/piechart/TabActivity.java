package com.example.user.piechart;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

public class TabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager_main);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTab();

    }

    private void setupViewPager(ViewPager viewPager) {
       ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(new FilterFragment(), "First");
        pagerAdapter.addFrag(new PieChartFragment(), "Second");
        viewPager.setAdapter(pagerAdapter);
    }

    private void setupTab() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabOne.setText("Tab One");
        tabLayout.getTabAt(0).setCustomView(tabOne);


        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabTwo.setText("Tab Two");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

    }
}
