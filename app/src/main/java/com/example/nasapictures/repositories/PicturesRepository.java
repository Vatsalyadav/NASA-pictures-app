package com.example.nasapictures.repositories;

import android.content.Context;

import com.example.nasapictures.models.PictureDetails;
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
public class PicturesRepository {

    private static PicturesRepository instance;
    private ArrayList<PictureDetails> picturesDataSet = new ArrayList<>();

    public static PicturesRepository getInstance() {
        if (instance == null) {
            instance = new PicturesRepository();
        }
        return instance;
    }


    public MutableLiveData<List<PictureDetails>> getPicturesData(Context context) {
        setPicturesData(context);
        MutableLiveData<List<PictureDetails>> data = new MutableLiveData<>();
        data.setValue(picturesDataSet);
        return data;
    }

    private String loadJSONFromAsset(Context context) {
        String picturesDataJson = null;
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            picturesDataJson = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return picturesDataJson;
    }

    private void setPicturesData(Context context) {
        picturesDataSet.clear();
        String dataJson = loadJSONFromAsset(context);
        try {
            JSONArray jsonArray = new JSONArray(dataJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                picturesDataSet.add(
                        new Gson().fromJson(jsonObject.toString(), PictureDetails.class)
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}