package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportCompleteInfoResponse {
    @SerializedName("airport")
    @Expose
    private Airport airport;
    @SerializedName("destination")
    @Expose
    private Destination destination;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("region")
    @Expose
    private Region region;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
