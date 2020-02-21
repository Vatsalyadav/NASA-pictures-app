package com.example.nasapictures.repositories;

import android.content.Context;

import com.example.nasapictures.models.PhotoDetails;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;


/**
 * Singleton pattern
 */
public class PhotosRepository {

    private static PhotosRepository instance;
    private ArrayList<PhotoDetails> photosDataSet = new ArrayList<>();

    public static PhotosRepository getInstance() {
        if (instance == null) {
            instance = new PhotosRepository();
        }
        return instance;
    }


    public MutableLiveData<List<PhotoDetails>> getPhotosData(Context context) {
        setPhotosData(context);
        MutableLiveData<List<PhotoDetails>> data = new MutableLiveData<>();
        data.setValue(photosDataSet);
        return data;
    }

    private String loadJSONFromAsset(Context context) {
        String photosDataJson = null;
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            photosDataJson = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return photosDataJson;
    }

    private void setPhotosData(Context context) {
        photosDataSet.clear();
        String dataJson = loadJSONFromAsset(context);
        try {
            JSONArray jsonArray = new JSONArray(dataJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                photosDataSet.add(
                        new Gson().fromJson(jsonObject.toString(), PhotoDetails.class)
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}