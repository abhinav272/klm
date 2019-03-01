package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMasterDataResponse {
    @SerializedName("airports")
    @Expose
    private List<Airport> airports = null;
    @SerializedName("destinations")
    @Expose
    private List<Destination> destinations = null;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;
    @SerializedName("regions")
    @Expose
    private List<Region> regions = null;

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
