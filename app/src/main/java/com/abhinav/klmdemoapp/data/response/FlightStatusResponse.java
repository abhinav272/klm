package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightStatusResponse {
    @SerializedName("flightNumber")
    @Expose
    private int flightNumber;
    @SerializedName("flightScheduleDate")
    @Expose
    private String flightScheduleDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("haul")
    @Expose
    private String haul;
    @SerializedName("route")
    @Expose
    private List<String> route = null;
    @SerializedName("airline")
    @Expose
    private Airline airline;
    @SerializedName("flightRelations")
    @Expose
    private FlightRelations flightRelations;
    @SerializedName("flightStatusPublic")
    @Expose
    private String flightStatusPublic;
    @SerializedName("flightStatusPublicLangTransl")
    @Expose
    private String flightStatusPublicLangTransl;
    @SerializedName("flightLegs")
    @Expose
    private List<FlightLeg> flightLegs = null;
    @SerializedName("internalStatusArrFocus")
    @Expose
    private boolean internalStatusArrFocus;

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightScheduleDate() {
        return flightScheduleDate;
    }

    public void setFlightScheduleDate(String flightScheduleDate) {
        this.flightScheduleDate = flightScheduleDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHaul() {
        return haul;
    }

    public void setHaul(String haul) {
        this.haul = haul;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public FlightRelations getFlightRelations() {
        return flightRelations;
    }

    public void setFlightRelations(FlightRelations flightRelations) {
        this.flightRelations = flightRelations;
    }

    public String getFlightStatusPublic() {
        return flightStatusPublic;
    }

    public void setFlightStatusPublic(String flightStatusPublic) {
        this.flightStatusPublic = flightStatusPublic;
    }

    public String getFlightStatusPublicLangTransl() {
        return flightStatusPublicLangTransl;
    }

    public void setFlightStatusPublicLangTransl(String flightStatusPublicLangTransl) {
        this.flightStatusPublicLangTransl = flightStatusPublicLangTransl;
    }

    public List<FlightLeg> getFlightLegs() {
        return flightLegs;
    }

    public void setFlightLegs(List<FlightLeg> flightLegs) {
        this.flightLegs = flightLegs;
    }

    public boolean isInternalStatusArrFocus() {
        return internalStatusArrFocus;
    }

    public void setInternalStatusArrFocus(boolean internalStatusArrFocus) {
        this.internalStatusArrFocus = internalStatusArrFocus;
    }
}
