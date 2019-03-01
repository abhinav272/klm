package com.abhinav.klmdemoapp.data.repo;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkCallback;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.ApiManager;
import com.abhinav.klmdemoapp.data.CacheManager;
import com.abhinav.klmdemoapp.data.response.AllMasterDataResponse;

public class MainActivityRepo {

    private static final String TAG = "MainActivityRepo";

    public MutableLiveData<NetworkState> fetchMasterData() {
        MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
        networkStateLiveData.setValue(NetworkState.RUNNING);
        ApiManager.getInstance().fetchMasterData().enqueue(new NetworkCallback<AllMasterDataResponse>() {
            @Override
            public void onSuccess(AllMasterDataResponse allMasterDataResponse) {
                CacheManager.getInstance().saveMasterData(allMasterDataResponse);
                networkStateLiveData.setValue(NetworkState.LOADED);
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                Log.e(TAG, "onFailure: " + failureResponse.getErrorMessage());
                networkStateLiveData.setValue(NetworkState.FAILED);
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ", t);
                networkStateLiveData.setValue(NetworkState.FAILED);
            }
        });

        return networkStateLiveData;
    }
}
