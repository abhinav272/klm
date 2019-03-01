package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeparturePlaces {

    @SerializedName("checkInAerogare")
    @Expose
    private String checkInAerogare;
    @SerializedName("terminalCode")
    @Expose
    private String terminalCode;

    public String getCheckInAerogare() {
        return checkInAerogare;
    }

    public void setCheckInAerogare(String checkInAerogare) {
        this.checkInAerogare = checkInAerogare;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
