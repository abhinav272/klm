package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureInformation {
    @SerializedName("airport")
    @Expose
    private AirportInfoDeparture airport;
    @SerializedName("times")
    @Expose
    private Times times;

    public AirportInfoDeparture getAirport() {
        return airport;
    }

    public void setAirport(AirportInfoDeparture airport) {
        this.airport = airport;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }
}
