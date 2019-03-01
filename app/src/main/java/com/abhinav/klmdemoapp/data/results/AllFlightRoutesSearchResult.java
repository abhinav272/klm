package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.data.response.FlightStatusByOriginDestinationResponse;

public class AllFlightRoutesSearchResult extends BaseSearchResult {

    private LiveData<FlightStatusByOriginDestinationResponse> liveData;

    public LiveData<FlightStatusByOriginDestinationResponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData<FlightStatusByOriginDestinationResponse> liveData) {
        this.liveData = liveData;
    }
}
