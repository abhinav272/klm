package com.abhinav.klmdemoapp.data.repo;

import android.arch.lifecycle.MutableLiveData;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkCallback;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.ApiManager;
import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;
import com.abhinav.klmdemoapp.data.response.Destination;
import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;
import com.abhinav.klmdemoapp.data.results.AirportCompleteInfoResult;
import com.abhinav.klmdemoapp.data.results.DestinationDetailResult;

public class DestinationRepo {

    public DestinationDetailResult getDetails(Destination destination) {
        MutableLiveData<DestinationDetailsResponse> responseLiveData = new MutableLiveData<>();
        MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
        MutableLiveData<FailureResponse> failureResponseLiveData = new MutableLiveData<>();

        DestinationDetailResult result = new DestinationDetailResult();
        result.setLiveData(responseLiveData);
        result.setFailureResponseLiveData(failureResponseLiveData);
        result.setNetworkStateLiveData(networkStateLiveData);

        networkStateLiveData.setValue(NetworkState.RUNNING);
        ApiManager.getInstance().getDestinationDetail(destination.getCode()).enqueue(new NetworkCallback<DestinationDetailsResponse>() {
            @Override
            public void onSuccess(DestinationDetailsResponse destinationDetailsResponse) {
                networkStateLiveData.setValue(NetworkState.LOADED);
                responseLiveData.setValue(destinationDetailsResponse);
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                networkStateLiveData.setValue(NetworkState.FAILED);
                failureResponseLiveData.setValue(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                failureResponseLiveData.setValue(FailureResponse.getGenericError());
            }
        });

        return result;
    }

    public AirportCompleteInfoResult getAirportDetails(String cityCode) {

        AirportCompleteInfoResult result = new AirportCompleteInfoResult();
        MutableLiveData<AirportCompleteInfoResponse> responseLiveData = new MutableLiveData<>();
        MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
        MutableLiveData<FailureResponse> failureResponseLiveData = new MutableLiveData<>();
        result.setLiveData(responseLiveData);
        result.setNetworkStateLiveData(networkStateLiveData);
        result.setFailureResponseLiveData(failureResponseLiveData);

        networkStateLiveData.setValue(NetworkState.RUNNING);
        ApiManager.getInstance().getAirportDetails(cityCode).enqueue(new NetworkCallback<AirportCompleteInfoResponse>() {
            @Override
            public void onSuccess(AirportCompleteInfoResponse airportCompleteInfoResponse) {
                networkStateLiveData.setValue(NetworkState.LOADED);
                responseLiveData.setValue(airportCompleteInfoResponse);
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                networkStateLiveData.setValue(NetworkState.FAILED);
                failureResponseLiveData.setValue(failureResponse);
            }

            @Override
            public void onError(Throwable t) {
                failureResponseLiveData.setValue(FailureResponse.getGenericError());
            }
        });

        return result;
    }
}
