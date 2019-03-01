package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;

public class AirportCompleteInfoResult extends BaseSearchResult {

    private LiveData<AirportCompleteInfoResponse> liveData;

    public LiveData<AirportCompleteInfoResponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData<AirportCompleteInfoResponse> liveData) {
        this.liveData = liveData;
    }
}
