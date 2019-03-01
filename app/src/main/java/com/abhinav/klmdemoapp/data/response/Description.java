package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("pictoUrl")
    @Expose
    private String pictoUrl;
    @SerializedName("pictoUrlSvg")
    @Expose
    private String pictoUrlSvg;
    @SerializedName("description")
    @Expose
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPictoUrl() {
        return pictoUrl;
    }

    public void setPictoUrl(String pictoUrl) {
        this.pictoUrl = pictoUrl;
    }

    public String getPictoUrlSvg() {
        return pictoUrlSvg;
    }

    public void setPictoUrlSvg(String pictoUrlSvg) {
        this.pictoUrlSvg = pictoUrlSvg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
