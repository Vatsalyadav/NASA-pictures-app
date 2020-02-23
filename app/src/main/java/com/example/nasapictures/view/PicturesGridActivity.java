package com.example.nasapictures.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.nasapictures.R;
import com.example.nasapictures.adapters.PicturesGridRecyclerAdapter;
import com.example.nasapictures.models.PictureDetails;
import com.example.nasapictures.viewmodels.PicturesGridActivityViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesGridActivity extends AppCompatActivity {

    private static final String TAG = "PhotosGridActivity";

    private RecyclerView mRecyclerView;
    private PicturesGridRecyclerAdapter mPicturesGridRecyclerAdapter;
    private PicturesGridActivityViewModel mPicturesGridActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures_grid);
        displayToolbar();
        initPicturesGridModelView();
        initRecyclerView();
    }

    private void displayToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pictures_grid);
        setSupportActionBar(toolbar);
    }

    // Initialize PicturesGridModelView to get UI data from repository
    private void initPicturesGridModelView() {
        try {
            mPicturesGridActivityViewModel = new ViewModelProvider(this).get(PicturesGridActivityViewModel.class);
            mPicturesGridActivityViewModel.init(this);
            mPicturesGridActivityViewModel.getPictureDetails().observe(this, new Observer<List<PictureDetails>>() {
                @Override
                public void onChanged(@Nullable List<PictureDetails> nicePlaces) {
                    mPicturesGridRecyclerAdapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error on initiating PicturesGridActivityViewModel to get UI Data");
            e.printStackTrace();
            Toast.makeText(this, "Unable to get UI Data, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    // Initialize RecyclerView to display NASA Pictures grid
    private void initRecyclerView() {
        try {
            mRecyclerView = findViewById(R.id.recycler_view_pictures_grid);
            mPicturesGridRecyclerAdapter = new PicturesGridRecyclerAdapter(this, mPicturesGridActivityViewModel.getPictureDetails().getValue());
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            mRecyclerView.setAdapter(mPicturesGridRecyclerAdapter);
        } catch (Exception e) {
            Log.e(TAG, "Error on initiating recyclerView to set NASA Pictures grid");
            e.printStackTrace();
            Toast.makeText(this, "Unable to set NASA Pictures grid UI Data, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
