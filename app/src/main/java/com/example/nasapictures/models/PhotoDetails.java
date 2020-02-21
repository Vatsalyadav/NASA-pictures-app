package com.example.nasapictures.models;

public class PhotoDetails {

    private String copyright;
    private String date;
    private String explanation;
    private String imageUrlHd;
    private String media_type;
    private String service_version;
    private String title;
    private String imageUrl;

    public PhotoDetails(String copyright, String date, String explanation,
                        String imageUrlHd, String media_type, String service_version,
                        String imageUrl, String title) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.copyright = copyright;
        this.date = date;
        this.explanation = imageUrlHd;
        this.media_type = media_type;
        this.service_version = service_version;
        this.imageUrlHd = imageUrlHd;
    }

    public PhotoDetails() {
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getImageUrlHd() {
        return imageUrlHd;
    }

    public void setImageUrlHd(String imageUrlHd) {
        this.imageUrlHd = imageUrlHd;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
