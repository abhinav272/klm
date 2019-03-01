package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightStatusByOriginDestinationResponse {

    @SerializedName("operationalFlights")
    @Expose
    private List<OperationalFlight> operationalFlights = null;
    @SerializedName("page")
    @Expose
    private Page page;

    public List<OperationalFlight> getOperationalFlights() {
        return operationalFlights;
    }

    public void setOperationalFlights(List<OperationalFlight> operationalFlights) {
        this.operationalFlights = operationalFlights;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
