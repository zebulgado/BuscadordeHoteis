package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.view.adapter.PageViewAdapter;

public class ViewPagerActivityHotel extends AppCompatActivity {

    private ViewPager mPager;
    private PageViewAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.viewPager);
        pagerAdapter = new PageViewAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }
}