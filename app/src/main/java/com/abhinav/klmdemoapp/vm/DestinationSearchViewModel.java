package com.abhinav.klmdemoapp.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.repo.DestinationRepo;
import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;
import com.abhinav.klmdemoapp.data.response.Destination;
import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;
import com.abhinav.klmdemoapp.data.response.Temperature;
import com.abhinav.klmdemoapp.data.results.AirportCompleteInfoResult;
import com.abhinav.klmdemoapp.data.results.BaseSearchResult;
import com.abhinav.klmdemoapp.data.results.DestinationDetailResult;

public class DestinationSearchViewModel extends ViewModel {

    MutableLiveData<Destination> queryLiveData;
    LiveData<String> queryAirportLiveData;
    LiveData<DestinationDetailResult> resultLiveData;
    private DestinationRepo repo = new DestinationRepo();
    private LiveData<DestinationDetailsResponse> responseLiveData;
    private LiveData<FailureResponse> failureResponseLiveData;
    private LiveData<NetworkState> networkStateLiveData;
    private LiveData<AirportCompleteInfoResult> airportResultLiveData;
    private LiveData<AirportCompleteInfoResponse> airportLiveData;


    public void initLiveData() {
        queryLiveData = new MutableLiveData<>();
        resultLiveData = Transformations.map(queryLiveData, this::apply);
        responseLiveData = Transformations.switchMap(resultLiveData, DestinationDetailResult::getLiveData);
        queryAirportLiveData = Transformations.map(responseLiveData, DestinationDetailsResponse::getCityCode);
        failureResponseLiveData = Transformations.switchMap(resultLiveData, BaseSearchResult::getFailureResponseLiveData);
        networkStateLiveData = Transformations.switchMap(resultLiveData, BaseSearchResult::getNetworkStateLiveData);
        airportResultLiveData = Transformations.map(queryAirportLiveData, this::getAirportByCode);
        airportLiveData = Transformations.switchMap(airportResultLiveData, AirportCompleteInfoResult::getLiveData);
    }

    public LiveData<AirportCompleteInfoResponse> getAirportLiveData() {
        return airportLiveData;
    }

    public LiveData<DestinationDetailsResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<FailureResponse> getFailureResponseLiveData() {
        return failureResponseLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }

    private DestinationDetailResult apply(Destination destination) {
        return repo.getDetails(destination);
    }

    public void onDestinationSelected(Destination destination) {
        queryLiveData.setValue(destination);
    }

    public AirportCompleteInfoResult getAirportByCode(String cityCode) {
        return repo.getAirportDetails(cityCode);
    }

    public String getFormattedTemp(Temperature temp) {
        return String.format("%s %s", String.valueOf(temp.getValue()), temp.getUnit());
    }
}
