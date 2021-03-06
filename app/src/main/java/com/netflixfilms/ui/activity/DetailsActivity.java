package com.netflixfilms.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.netflixfilms.R;
import com.netflixfilms.adapter.FilmPagerAdapter;
import com.netflixfilms.model.Film;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.details_toolbar)
    Toolbar toolbar;
    @Bind(R.id.details_coordination_layout)
    CoordinatorLayout coordinatorLayout;

    public static final String DETAILS_KEY_POSITION = "position";
    public static final String DETAILS_KEY = "details_key";
    private static final String POSITION_STATE = "position";
    private static final String TAG = DetailsActivity.class.getSimpleName();

    private List<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        filmList = getIntent().getParcelableArrayListExtra(DETAILS_KEY);
        viewPager.setAdapter(new FilmPagerAdapter(getSupportFragmentManager(), filmList));
        viewPager.setCurrentItem(getIntent().getIntExtra(DETAILS_KEY_POSITION, 0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(filmList.get(position).getShowTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.save_btn)
    void saveFilm() {
        Log.i(TAG, "saveFilm: toolbar button clicked");
        Film film = filmList.get(viewPager.getCurrentItem());
        film.save();
        Snackbar.make(coordinatorLayout, "film saved", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(POSITION_STATE, viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            viewPager.setCurrentItem(savedInstanceState.getInt(POSITION_STATE));
        }
    }

}
