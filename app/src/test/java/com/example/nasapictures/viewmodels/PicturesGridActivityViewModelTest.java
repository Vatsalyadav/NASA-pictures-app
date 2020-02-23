package com.example.nasapictures.viewmodels;

import com.example.nasapictures.data.PictureDetailsTestData;
import com.example.nasapictures.models.PictureDetails;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PicturesGridActivityViewModelTest {

    private PictureDetails mPictureDetails;

    @Before
    public void setUpDetailViewModelTest() {
        mPictureDetails = PictureDetailsTestData.getPictureDetails();
    }

    @Test
    public void getPictureDetails() {
        String picturesDataJson = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("assets/testdata.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            picturesDataJson = new String(buffer, "UTF-8");

            ArrayList<PictureDetails> picturesDataSet = new ArrayList<>();
            picturesDataSet.add(
                    new Gson().fromJson(new String(picturesDataJson.getBytes("ISO-8859-1"), "UTF-8"), PictureDetails.class)
            );
            assertEquals(picturesDataSet.get(0).getTitle(), mPictureDetails.getTitle());
            assertEquals(picturesDataSet.get(0).getCopyright(), mPictureDetails.getCopyright());
            assertEquals(picturesDataSet.get(0).getDate(), mPictureDetails.getDate());
            assertEquals(picturesDataSet.get(0).getHdurl(), mPictureDetails.getHdurl());
            assertEquals(picturesDataSet.get(0).getUrl(), mPictureDetails.getUrl());
            assertEquals(picturesDataSet.get(0).getMedia_type(), mPictureDetails.getMedia_type());
            assertEquals(picturesDataSet.get(0).getService_version(), mPictureDetails.getService_version());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}