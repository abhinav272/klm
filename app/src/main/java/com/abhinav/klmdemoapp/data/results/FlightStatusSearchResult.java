package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;

public class FlightStatusSearchResult extends BaseSearchResult {
    private LiveData<FlightStatusResponse> liveData;

    public LiveData<FlightStatusResponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData<FlightStatusResponse> liveData) {
        this.liveData = liveData;
    }
}
