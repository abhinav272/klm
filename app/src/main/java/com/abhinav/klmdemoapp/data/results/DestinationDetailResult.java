package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;

public class DestinationDetailResult extends BaseSearchResult {
    private LiveData<DestinationDetailsResponse> liveData;

    public LiveData<DestinationDetailsResponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData<DestinationDetailsResponse> liveData) {
        this.liveData = liveData;
    }
}
