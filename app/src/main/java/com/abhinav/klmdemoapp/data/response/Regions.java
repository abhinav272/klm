package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regions {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("regionLabel")
    @Expose
    private String regionLabel;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("latitude")
    @Expose
    private float latitude;
    @SerializedName("longitude")
    @Expose
    private float longitude;
    @SerializedName("travelGuide")
    @Expose
    private TravelGuide travelGuide;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegionLabel() {
        return regionLabel;
    }

    public void setRegionLabel(String regionLabel) {
        this.regionLabel = regionLabel;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public TravelGuide getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(TravelGuide travelGuide) {
        this.travelGuide = travelGuide;
    }
}
