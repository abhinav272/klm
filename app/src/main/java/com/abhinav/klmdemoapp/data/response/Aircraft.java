package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aircraft {
    @SerializedName("registration")
    @Expose
    private String registration;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("subFleetCodeId")
    @Expose
    private String subFleetCodeId;
    @SerializedName("ownerAirlineCode")
    @Expose
    private String ownerAirlineCode;
    @SerializedName("ownerAirlineName")
    @Expose
    private String ownerAirlineName;
    @SerializedName("physicalPaxConfiguration")
    @Expose
    private String physicalPaxConfiguration;
    @SerializedName("physicalFreightConfiguration")
    @Expose
    private String physicalFreightConfiguration;
    @SerializedName("operationalConfiguration")
    @Expose
    private String operationalConfiguration;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubFleetCodeId() {
        return subFleetCodeId;
    }

    public void setSubFleetCodeId(String subFleetCodeId) {
        this.subFleetCodeId = subFleetCodeId;
    }

    public String getOwnerAirlineCode() {
        return ownerAirlineCode;
    }

    public void setOwnerAirlineCode(String ownerAirlineCode) {
        this.ownerAirlineCode = ownerAirlineCode;
    }

    public String getOwnerAirlineName() {
        return ownerAirlineName;
    }

    public void setOwnerAirlineName(String ownerAirlineName) {
        this.ownerAirlineName = ownerAirlineName;
    }

    public String getPhysicalPaxConfiguration() {
        return physicalPaxConfiguration;
    }

    public void setPhysicalPaxConfiguration(String physicalPaxConfiguration) {
        this.physicalPaxConfiguration = physicalPaxConfiguration;
    }

    public String getPhysicalFreightConfiguration() {
        return physicalFreightConfiguration;
    }

    public void setPhysicalFreightConfiguration(String physicalFreightConfiguration) {
        this.physicalFreightConfiguration = physicalFreightConfiguration;
    }

    public String getOperationalConfiguration() {
        return operationalConfiguration;
    }

    public void setOperationalConfiguration(String operationalConfiguration) {
        this.operationalConfiguration = operationalConfiguration;
    }
}
