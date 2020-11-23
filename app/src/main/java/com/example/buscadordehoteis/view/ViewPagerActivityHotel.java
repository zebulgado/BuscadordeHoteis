package com.example.buscadordehoteis.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buscadordehoteis.R;
import com.example.buscadordehoteis.model.Hotel;
import com.example.buscadordehoteis.repository.RetrofitConfig;
import com.example.buscadordehoteis.view.adapter.PageViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerActivityHotel extends AppCompatActivity {

    private ViewPager mPager;
    private PageViewAdapter pagerAdapter;
    private List<Hotel> hotelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        RetrofitConfig retrofitConfig = new RetrofitConfig();
        Call<List<Hotel>> getAllRequestCall = retrofitConfig.getHotelService().getAllHotel();
        getAllRequestCall.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                hotelList = response.body();
                mPager = findViewById(R.id.viewPager);
                pagerAdapter = new PageViewAdapter(getSupportFragmentManager(), hotelList);
                mPager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e("GuestService   ", "Erro ao buscar o guest:" + t.getMessage());
            }
        });

//        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            // This method will be invoked when a new page becomes selected.
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            // This method will be invoked when the current page is scrolled
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            // Called when the scroll state changes:
//            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }
}