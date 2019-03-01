package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkState;

public class BaseSearchResult {

    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<FailureResponse> failureResponseLiveData;

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    public void setNetworkStateLiveData(LiveData<NetworkState> networkStateLiveData) {
        this.networkStateLiveData = networkStateLiveData;
    }

    public LiveData<FailureResponse> getFailureResponseLiveData() {
        return failureResponseLiveData;
    }

    public void setFailureResponseLiveData(LiveData<FailureResponse> failureResponseLiveData) {
        this.failureResponseLiveData = failureResponseLiveData;
    }
}
