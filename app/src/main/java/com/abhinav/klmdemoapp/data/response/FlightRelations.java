package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightRelations {
    @SerializedName("onwardFlightData")
    @Expose
    private OnwardFlightData onwardFlightData;

    public OnwardFlightData getOnwardFlightData() {
        return onwardFlightData;
    }

    public void setOnwardFlightData(OnwardFlightData onwardFlightData) {
        this.onwardFlightData = onwardFlightData;
    }
}
