package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightLeg {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusName")
    @Expose
    private String statusName;
    @SerializedName("publishedStatus")
    @Expose
    private String publishedStatus;
    @SerializedName("departureInformation")
    @Expose
    private DepartureInformation departureInformation;
    @SerializedName("arrivalInformation")
    @Expose
    private ArrivalInformation arrivalInformation;
    @SerializedName("legStatusPublic")
    @Expose
    private String legStatusPublic;
    @SerializedName("legStatusPublicLangTransl")
    @Expose
    private String legStatusPublicLangTransl;
    @SerializedName("passengerCustomsStatus")
    @Expose
    private String passengerCustomsStatus;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("serviceTypeName")
    @Expose
    private String serviceTypeName;
    @SerializedName("scheduledFlightDuration")
    @Expose
    private String scheduledFlightDuration;
    @SerializedName("completionPercentage")
    @Expose
    private String completionPercentage;
    @SerializedName("timeZoneDifference")
    @Expose
    private String timeZoneDifference;
    @SerializedName("aircraft")
    @Expose
    private Aircraft aircraft;
    @SerializedName("irregularity")
    @Expose
    private Irregularity irregularity;
    @SerializedName("internalLegStatusArrFocus")
    @Expose
    private boolean internalLegStatusArrFocus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPublishedStatus() {
        return publishedStatus;
    }

    public void setPublishedStatus(String publishedStatus) {
        this.publishedStatus = publishedStatus;
    }

    public DepartureInformation getDepartureInformation() {
        return departureInformation;
    }

    public void setDepartureInformation(DepartureInformation departureInformation) {
        this.departureInformation = departureInformation;
    }

    public ArrivalInformation getArrivalInformation() {
        return arrivalInformation;
    }

    public void setArrivalInformation(ArrivalInformation arrivalInformation) {
        this.arrivalInformation = arrivalInformation;
    }

    public String getLegStatusPublic() {
        return legStatusPublic;
    }

    public void setLegStatusPublic(String legStatusPublic) {
        this.legStatusPublic = legStatusPublic;
    }

    public String getLegStatusPublicLangTransl() {
        return legStatusPublicLangTransl;
    }

    public void setLegStatusPublicLangTransl(String legStatusPublicLangTransl) {
        this.legStatusPublicLangTransl = legStatusPublicLangTransl;
    }

    public String getPassengerCustomsStatus() {
        return passengerCustomsStatus;
    }

    public void setPassengerCustomsStatus(String passengerCustomsStatus) {
        this.passengerCustomsStatus = passengerCustomsStatus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getScheduledFlightDuration() {
        return scheduledFlightDuration;
    }

    public void setScheduledFlightDuration(String scheduledFlightDuration) {
        this.scheduledFlightDuration = scheduledFlightDuration;
    }

    public String getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(String completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getTimeZoneDifference() {
        return timeZoneDifference;
    }

    public void setTimeZoneDifference(String timeZoneDifference) {
        this.timeZoneDifference = timeZoneDifference;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Irregularity getIrregularity() {
        return irregularity;
    }

    public void setIrregularity(Irregularity irregularity) {
        this.irregularity = irregularity;
    }

    public boolean isInternalLegStatusArrFocus() {
        return internalLegStatusArrFocus;
    }

    public void setInternalLegStatusArrFocus(boolean internalLegStatusArrFocus) {
        this.internalLegStatusArrFocus = internalLegStatusArrFocus;
    }
}
