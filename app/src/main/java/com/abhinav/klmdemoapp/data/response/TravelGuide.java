package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelGuide {
    @SerializedName("isTravelGuideAvailable")
    @Expose
    private boolean isTravelGuideAvailable;
    @SerializedName("travelGuideUrl")
    @Expose
    private String travelGuideUrl;
    @SerializedName("flightBookingUrl")
    @Expose
    private String flightBookingUrl;
    @SerializedName("practicalInformationUrl")
    @Expose
    private String practicalInformationUrl;

    public boolean isIsTravelGuideAvailable() {
        return isTravelGuideAvailable;
    }

    public void setIsTravelGuideAvailable(boolean isTravelGuideAvailable) {
        this.isTravelGuideAvailable = isTravelGuideAvailable;
    }

    public String getTravelGuideUrl() {
        return travelGuideUrl;
    }

    public void setTravelGuideUrl(String travelGuideUrl) {
        this.travelGuideUrl = travelGuideUrl;
    }

    public String getFlightBookingUrl() {
        return flightBookingUrl;
    }

    public void setFlightBookingUrl(String flightBookingUrl) {
        this.flightBookingUrl = flightBookingUrl;
    }

    public String getPracticalInformationUrl() {
        return practicalInformationUrl;
    }

    public void setPracticalInformationUrl(String practicalInformationUrl) {
        this.practicalInformationUrl = practicalInformationUrl;
    }
}
