package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeShareRelation {
    @SerializedName("marketingFlightNumber")
    @Expose
    private int marketingFlightNumber;
    @SerializedName("flightScheduleDate")
    @Expose
    private String flightScheduleDate;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("airline")
    @Expose
    private Airline airline;

    public int getMarketingFlightNumber() {
        return marketingFlightNumber;
    }

    public void setMarketingFlightNumber(int marketingFlightNumber) {
        this.marketingFlightNumber = marketingFlightNumber;
    }

    public String getFlightScheduleDate() {
        return flightScheduleDate;
    }

    public void setFlightScheduleDate(String flightScheduleDate) {
        this.flightScheduleDate = flightScheduleDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
