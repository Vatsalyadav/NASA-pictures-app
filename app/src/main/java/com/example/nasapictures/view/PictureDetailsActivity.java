package com.example.nasapictures.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nasapictures.R;
import com.example.nasapictures.adapters.PictureDetailsViewPagerAdapter;
import com.example.nasapictures.models.PictureDetails;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PictureDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_SELECTED_POSITION = "EXTRA_SELECTED_POSITION";
    private static final String EXTRA_PICTURES_DETAILS = "EXTRA_PICTURES_DETAILS";
    private static final String TAG = "PictureDetailsActivity";

    ViewPager picturesViewPager;
    private int mSelectedPicturePosition;
    private List<PictureDetails> mPictureDetailsList;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_details);
        getExtrasFromIntent(getIntent());
        displayHomeAsUpEnabled();
        actionBar = getSupportActionBar();
        setScreenTitle(actionBar, mPictureDetailsList.get(mSelectedPicturePosition).getTitle());
        setupPicturesViewPager();
    }

    // Get Selected Picture Position and PictureDetailsList data from Intent
    public void getExtrasFromIntent(Intent photoDetailsIntent) {
        try {
            mSelectedPicturePosition = (int) photoDetailsIntent.getSerializableExtra(EXTRA_SELECTED_POSITION);
            mPictureDetailsList = (List<PictureDetails>) photoDetailsIntent.getSerializableExtra(EXTRA_PICTURES_DETAILS);
        } catch (Exception e) {
            Log.e(TAG, "Error while getting Picture Details from Intent");
            e.printStackTrace();
            Toast.makeText(this, "Unable to fetch NASA picture details, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayHomeAsUpEnabled() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_picture_details);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setScreenTitle(ActionBar actionBar, String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    // Set up View Pager for Swiping through screen for NASA pictures
    public void setupPicturesViewPager() {
        try {
            picturesViewPager = (ViewPager) findViewById(R.id.view_pager_picture_details);
            PagerAdapter adapter = new PictureDetailsViewPagerAdapter(this, this, mPictureDetailsList);
            picturesViewPager.setAdapter(adapter);
            picturesViewPager.setCurrentItem(mSelectedPicturePosition);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_selector);
            tabLayout.setupWithViewPager(picturesViewPager, true);
            viewPagerChangeListener();
        } catch (Exception e) {
            Log.e(TAG, "Error while setting Picture View Pager");
            e.printStackTrace();
            Toast.makeText(this, "Unable to set Picture Details swipe, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    // Listen to changes in View Pager scroll
    private void viewPagerChangeListener() {
        picturesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setScreenTitle(actionBar, mPictureDetailsList.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // Create intent for PictureDetailsActivity with data to initiate it
    public Intent launchPictureDetails(Context context, int selectedPicturePosition, List<PictureDetails> pictureDetailsList) {
        Intent intent = new Intent(context, PictureDetailsActivity.class);
        intent.putExtra(EXTRA_SELECTED_POSITION, selectedPicturePosition);
        intent.putExtra(EXTRA_PICTURES_DETAILS, (ArrayList<PictureDetails>) pictureDetailsList);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}