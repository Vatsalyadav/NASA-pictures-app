package com.example.nasapictures.data;

import com.example.nasapictures.models.PictureDetails;

import java.util.ArrayList;
import java.util.List;

public class PictureDetailsTestData {

    private static final String PICTURE_COPYRIGHT = "ESA/HubbleNASA";
    private static final String PICTURE_DATE = "2019-12-01";
    private static final String PICTURE_EXPLANATION = "Why does this galaxy have a ring of bright blue stars?";
    private static final String PICTURE_HDURL = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg";
    private static final String PICTURE_MEDIA_TYPE = "image";
    private static final String PICTURE_SERVICE_VERSION = "v1";
    private static final String PICTURE_TITLE = "Starburst Galaxy M94 from Hubble";
    private static final String PICTURE_URL = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg";

    public static List<PictureDetails> getPictureDetailsList() {
        List<PictureDetails> pictureDetailsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pictureDetailsList.add(getPictureDetails());
        }
        return pictureDetailsList;
    }

    public static PictureDetails getPictureDetails() {
        PictureDetails pictureDetails = new PictureDetails();
        pictureDetails.setDate(PICTURE_DATE);
        pictureDetails.setCopyright(PICTURE_COPYRIGHT);
        pictureDetails.setExplanation(PICTURE_EXPLANATION);
        pictureDetails.setHdurl(PICTURE_HDURL);
        pictureDetails.setUrl(PICTURE_URL);
        pictureDetails.setMedia_type(PICTURE_MEDIA_TYPE);
        pictureDetails.setService_version(PICTURE_SERVICE_VERSION);
        pictureDetails.setTitle(PICTURE_TITLE);
        return pictureDetails;
    }
}
