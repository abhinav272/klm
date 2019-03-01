package com.abhinav.klmdemoapp.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.abhinav.klmdemoapp.R;
import com.abhinav.klmdemoapp.base.Event;
import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.CacheManager;
import com.abhinav.klmdemoapp.data.repo.FlightStatusRepo;
import com.abhinav.klmdemoapp.data.response.Airport;
import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;
import com.abhinav.klmdemoapp.data.results.BaseSearchResult;
import com.abhinav.klmdemoapp.data.results.FlightStatusSearchResult;
import com.abhinav.klmdemoapp.utils.ResourceUtil;

public class FlightStatusViewModel extends ViewModel {

    private MutableLiveData<Event<FailureResponse>> uiValidationLiveData;
    private MutableLiveData<LiveData<FlightStatusSearchResult>> searchResultLiveData;
    private FlightStatusRepo repo = new FlightStatusRepo();
    private LiveData<FlightStatusResponse> responseLiveData;
    private LiveData<FailureResponse> failureResponseLiveData;
    private LiveData<NetworkState> networkStateLiveData;

    public void initLiveData() {
        uiValidationLiveData = new MutableLiveData<>();
        searchResultLiveData = new MutableLiveData<>();
        LiveData<FlightStatusSearchResult> resultLiveData = Transformations.map(searchResultLiveData, LiveData::getValue);
        responseLiveData = Transformations.switchMap(resultLiveData, FlightStatusSearchResult::getLiveData);
        failureResponseLiveData = Transformations.switchMap(resultLiveData, BaseSearchResult::getFailureResponseLiveData);
        networkStateLiveData = Transformations.switchMap(resultLiveData, BaseSearchResult::getNetworkStateLiveData);
    }

    public LiveData<FlightStatusResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<FailureResponse> getFailureResponseLiveData() {
        return failureResponseLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    public void onSearchClicked(String origin, String flightNumber, String date) {
        if (fireValidations(origin, flightNumber, date)) {
            searchResultLiveData.setValue(repo.onSearchClicked(origin, flightNumber, date));
        }
    }

    public MutableLiveData<Event<FailureResponse>> getUiValidationLiveData() {
        return uiValidationLiveData;
    }

    private boolean fireValidations(String origin, String flightNumber, String date) {
        boolean allInputsValid = true;

        if (TextUtils.isEmpty(date)) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(101, ResourceUtil.getInstance().getString(R.string.error_date_empty));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        if (TextUtils.isEmpty(flightNumber)) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(102, ResourceUtil.getInstance().getString(R.string.error_destination_empty));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        if (TextUtils.isEmpty(origin)) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(103, ResourceUtil.getInstance().getString(R.string.error_origin_empty));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        if (CacheManager.getInstance().getDestinations() != null
                && !CacheManager.getInstance().getAirports().contains(new Airport(origin))) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(104, ResourceUtil.getInstance().getString(R.string.error_origin_invalid));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        return allInputsValid;
    }
}
