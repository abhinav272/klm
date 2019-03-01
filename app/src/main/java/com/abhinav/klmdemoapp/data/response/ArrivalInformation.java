package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalInformation {
    @SerializedName("airport")
    @Expose
    private AirportInfoArrival airport;
    @SerializedName("times")
    @Expose
    private Times times;

    public AirportInfoArrival getAirport() {
        return airport;
    }

    public void setAirport(AirportInfoArrival airport) {
        this.airport = airport;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }
}
