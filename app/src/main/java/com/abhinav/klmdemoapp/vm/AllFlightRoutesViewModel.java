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
import com.abhinav.klmdemoapp.data.repo.AllFlightRoutesRepo;
import com.abhinav.klmdemoapp.data.response.Airport;
import com.abhinav.klmdemoapp.data.response.Destination;
import com.abhinav.klmdemoapp.data.response.FlightStatusByOriginDestinationResponse;
import com.abhinav.klmdemoapp.data.response.OperationalFlight;
import com.abhinav.klmdemoapp.data.results.AllFlightRoutesSearchResult;
import com.abhinav.klmdemoapp.utils.ResourceUtil;

import java.util.List;

public class AllFlightRoutesViewModel extends ViewModel {

    private AllFlightRoutesRepo repo = new AllFlightRoutesRepo();
    private MutableLiveData<LiveData<AllFlightRoutesSearchResult>> searchResultLiveData;
    private LiveData<FlightStatusByOriginDestinationResponse> responseLiveData;
    private LiveData<FailureResponse> failureResponseLiveData;
    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<List<OperationalFlight>> listLiveData;
    private MutableLiveData<Event<FailureResponse>> uiValidationLiveData;

    public void initLiveData() {
        uiValidationLiveData = new MutableLiveData<>();
        searchResultLiveData = new MutableLiveData<>();
        LiveData<AllFlightRoutesSearchResult> resultLiveData = Transformations.map(searchResultLiveData, LiveData::getValue);
        responseLiveData = Transformations.switchMap(resultLiveData, AllFlightRoutesSearchResult::getLiveData);
        failureResponseLiveData = Transformations.switchMap(resultLiveData, AllFlightRoutesSearchResult::getFailureResponseLiveData);
        networkStateLiveData = Transformations.switchMap(resultLiveData, AllFlightRoutesSearchResult::getNetworkStateLiveData);
        listLiveData = Transformations.map(responseLiveData, FlightStatusByOriginDestinationResponse::getOperationalFlights);
    }

    public MutableLiveData<Event<FailureResponse>> getUiValidationLiveData() {
        return uiValidationLiveData;
    }

    public LiveData<List<OperationalFlight>> getListLiveData() {
        return listLiveData;
    }

    public LiveData<FailureResponse> getFailureResponseLiveData() {
        return failureResponseLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    public void onSearchClicked(String origin, String destination, String date) {
        if (fireValidations(origin, destination, date)) {
            searchResultLiveData.setValue(repo.onSearchClicked(origin, destination, date));
        }
    }

    private boolean fireValidations(String origin, String destination, String date) {
        boolean allInputsValid = true;

        if (TextUtils.isEmpty(date)) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(101, ResourceUtil.getInstance().getString(R.string.error_date_empty));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        if (TextUtils.isEmpty(destination)) {
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
                && !CacheManager.getInstance().getAirports().contains(new Airport(destination))) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(104, ResourceUtil.getInstance().getString(R.string.error_destination_invalid));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        if (CacheManager.getInstance().getDestinations() != null
                && !CacheManager.getInstance().getAirports().contains(new Airport(origin))) {
            allInputsValid = false;
            FailureResponse fr = new FailureResponse(105, ResourceUtil.getInstance().getString(R.string.error_origin_invalid));
            uiValidationLiveData.setValue(new Event<>(fr));
        }

        return allInputsValid;
    }
}
