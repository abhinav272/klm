package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportInfoDeparture {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("places")
    @Expose
    private DeparturePlaces places;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DeparturePlaces getDeparturePlaces() {
        return places;
    }

    public void setDeparturePlaces(DeparturePlaces places) {
        this.places = places;
    }
}
