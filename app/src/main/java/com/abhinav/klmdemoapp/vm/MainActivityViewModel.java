package com.abhinav.klmdemoapp.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.repo.MainActivityRepo;

public class MainActivityViewModel extends ViewModel {

    private MainActivityRepo repo = new MainActivityRepo();
    private MutableLiveData<NetworkState> networkStateMutableLiveData;

    public MutableLiveData<NetworkState> getNetworkStateMutableLiveData() {
        return networkStateMutableLiveData;
    }

    public void initLiveData() {
        networkStateMutableLiveData = repo.fetchMasterData();
    }
}
