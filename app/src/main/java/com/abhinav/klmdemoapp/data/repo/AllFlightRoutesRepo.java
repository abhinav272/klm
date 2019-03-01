package com.abhinav.klmdemoapp.data.repo;

import android.arch.lifecycle.MutableLiveData;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkCallback;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.ApiManager;
import com.abhinav.klmdemoapp.data.Constants;
import com.abhinav.klmdemoapp.data.response.FlightStatusByOriginDestinationResponse;
import com.abhinav.klmdemoapp.data.results.AllFlightRoutesSearchResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AllFlightRoutesRepo {

    public MutableLiveData<AllFlightRoutesSearchResult> onSearchClicked(String origin, String destination, String date) {
        MutableLiveData<AllFlightRoutesSearchResult> liveData = new MutableLiveData<>();
        MutableLiveData<FlightStatusByOriginDestinationResponse> responseLiveData = new MutableLiveData<>();
        MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
        MutableLiveData<FailureResponse> failureResponseLiveData = new MutableLiveData<>();
        String startDate = getStartDate(date);
        String endDate = getEndDate(date);
        networkStateLiveData.setValue(NetworkState.RUNNING);

        AllFlightRoutesSearchResult result = new AllFlightRoutesSearchResult();
        result.setLiveData(responseLiveData);
        result.setFailureResponseLiveData(failureResponseLiveData);
        result.setNetworkStateLiveData(networkStateLiveData);

        liveData.setValue(result);

        ApiManager.getInstance().getStatusByOriginAndDestination(origin, destination, startDate, endDate).enqueue(new NetworkCallback<FlightStatusByOriginDestinationResponse>() {
            @Override
            public void onSuccess(FlightStatusByOriginDestinationResponse response) {
                networkStateLiveData.setValue(NetworkState.LOADED);
                responseLiveData.setValue(response);
            }

            @Override
            public void onFailure(FailureResponse failureResponse) {
                failureResponseLiveData.setValue(failureResponse);
                networkStateLiveData.setValue(NetworkState.FAILED);
            }

            @Override
            public void onError(Throwable t) {
                networkStateLiveData.setValue(NetworkState.FAILED);
                failureResponseLiveData.setValue(FailureResponse.getGenericError());
            }
        });

        return liveData;
    }

    private String getStartDate(String date) {
        Date dateObj = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_PATTERN_USER_INPUT, Locale.getDefault());
        SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat(Constants.DATE_PATTERN_API_REQUEST_ALL_FLIGHT_ROUTES, Locale.getDefault());
        try {
            dateObj = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        if (dateObj != null) {
            instance.setTime(dateObj);
            instance.set(Calendar.HOUR_OF_DAY, 00);
            instance.set(Calendar.MINUTE, 00);
            instance.set(Calendar.SECOND, 00);
            return simpleDateFormatOutput.format(instance.getTime());
        }
        return "";
    }

    private String getEndDate(String date) {
        Date dateObj = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_PATTERN_USER_INPUT, Locale.getDefault());
        SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat(Constants.DATE_PATTERN_API_REQUEST_ALL_FLIGHT_ROUTES, Locale.getDefault());
        try {
            dateObj = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        if (dateObj != null) {
            instance.setTime(dateObj);
            instance.set(Calendar.HOUR_OF_DAY, 23);
            instance.set(Calendar.MINUTE, 59);
            instance.set(Calendar.SECOND, 59);
            return simpleDateFormatOutput.format(instance.getTime());
        }
        return "";
    }
}
