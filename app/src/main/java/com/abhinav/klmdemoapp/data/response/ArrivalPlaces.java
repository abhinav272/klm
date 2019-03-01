package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalPlaces {
    @SerializedName("aerogareCode")
    @Expose
    private String aerogareCode;
    @SerializedName("arrivalPositionTerminal")
    @Expose
    private String arrivalPositionTerminal;

    public String getAerogareCode() {
        return aerogareCode;
    }

    public void setAerogareCode(String aerogareCode) {
        this.aerogareCode = aerogareCode;
    }

    public String getArrivalPositionTerminal() {
        return arrivalPositionTerminal;
    }

    public void setArrivalPositionTerminal(String arrivalPositionTerminal) {
        this.arrivalPositionTerminal = arrivalPositionTerminal;
    }
}
