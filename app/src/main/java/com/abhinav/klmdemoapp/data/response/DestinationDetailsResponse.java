package com.abhinav.klmdemoapp.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DestinationDetailsResponse {
    @SerializedName("cityCode")
    @Expose
    private String cityCode;
    @SerializedName("spokenLanguages")
    @Expose
    private List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("weather")
    @Expose
    private Weather weather;
    //    @SerializedName("currency")
//    @Expose
//    private Currency currency;
    @SerializedName("travelGuide")
    @Expose
    private TravelGuide travelGuide;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

//    public Currency getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }

    public TravelGuide getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(TravelGuide travelGuide) {
        this.travelGuide = travelGuide;
    }
}
