package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnwardFlightData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("flightScheduleDate")
    @Expose
    private String flightScheduleDate;
    @SerializedName("airlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightScheduleDate() {
        return flightScheduleDate;
    }

    public void setFlightScheduleDate(String flightScheduleDate) {
        this.flightScheduleDate = flightScheduleDate;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
