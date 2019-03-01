package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("imageCaption")
    @Expose
    private String imageCaption;
    @SerializedName("imageAccessibility")
    @Expose
    private String imageAccessibility;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImageAccessibility() {
        return imageAccessibility;
    }

    public void setImageAccessibility(String imageAccessibility) {
        this.imageAccessibility = imageAccessibility;
    }
}
