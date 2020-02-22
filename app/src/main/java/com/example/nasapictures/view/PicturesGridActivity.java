package com.example.nasapictures.view;

import android.os.Bundle;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initPicturesGridModelView() {
        mPicturesGridActivityViewModel = new ViewModelProvider(this).get(PicturesGridActivityViewModel.class);
        mPicturesGridActivityViewModel.init(this);
        mPicturesGridActivityViewModel.getPictureDetails().observe(this, new Observer<List<PictureDetails>>() {
            @Override
            public void onChanged(@Nullable List<PictureDetails> nicePlaces) {
                mPicturesGridRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.pictures_grid_recycler_view);
        mPicturesGridRecyclerAdapter = new PicturesGridRecyclerAdapter(this, mPicturesGridActivityViewModel.getPictureDetails().getValue());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mPicturesGridRecyclerAdapter);
    }
}
