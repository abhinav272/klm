package com.abhinav.klmdemoapp.data.results;

import android.arch.lifecycle.LiveData;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.data.response.AllMasterDataResponse;

public class AllMasterDataResult {

    private LiveData<AllMasterDataResponse> liveData;
    private LiveData<FailureResponse> failureResponseLiveData;

    public LiveData<AllMasterDataResponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData<AllMasterDataResponse> liveData) {
        this.liveData = liveData;
    }

    public LiveData<FailureResponse> getFailureResponseLiveData() {
        return failureResponseLiveData;
    }

    public void setFailureResponseLiveData(LiveData<FailureResponse> failureResponseLiveData) {
        this.failureResponseLiveData = failureResponseLiveData;
    }
}
