package com.example.nasapictures.viewmodels;

import com.example.nasapictures.models.PictureDetails;
import com.example.nasapictures.repositories.PicturesRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PicturesGridActivityViewModel extends ViewModel {
    private MutableLiveData<List<PictureDetails>> mPictureDetails;
    private PicturesRepository mPicturesRepo;

    public void init() {
        if (mPictureDetails != null) {
            return;
        }
        mPicturesRepo = PicturesRepository.getInstance();
        mPictureDetails = mPicturesRepo.getPicturesData();
    }

    public LiveData<List<PictureDetails>> getPictureDetails() {
        return mPictureDetails;
    }
}
