package com.abhinav.klmdemoapp.data.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abhinav.klmdemoapp.base.FailureResponse;
import com.abhinav.klmdemoapp.base.NetworkCallback;
import com.abhinav.klmdemoapp.base.NetworkState;
import com.abhinav.klmdemoapp.data.ApiManager;
import com.abhinav.klmdemoapp.data.Constants;
import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;
import com.abhinav.klmdemoapp.data.results.FlightStatusSearchResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FlightStatusRepo {

    public LiveData<FlightStatusSearchResult> onSearchClicked(String origin, String flightNumber, String date) {
        MutableLiveData<FlightStatusSearchResult> liveData = new MutableLiveData<>();
        MutableLiveData<FlightStatusResponse> responseLiveData = new MutableLiveData<>();
        MutableLiveData<NetworkState> networkStateLiveData = new MutableLiveData<>();
        MutableLiveData<FailureResponse> failureResponseLiveData = new MutableLiveData<>();
        FlightStatusSearchResult result = new FlightStatusSearchResult();
        result.setLiveData(responseLiveData);
        result.setFailureResponseLiveData(failureResponseLiveData);
        result.setNetworkStateLiveData(networkStateLiveData);
        liveData.setValue(result);
        networkStateLiveData.setValue(NetworkState.RUNNING);

        String flightDateCombo = getFlightDateCombo(flightNumber, date);

        ApiManager.getInstance().getStatusByFlight(flightDateCombo, origin).enqueue(new NetworkCallback<FlightStatusResponse>() {
            @Override
            public void onSuccess(FlightStatusResponse flightStatusResponse) {
                networkStateLiveData.setValue(NetworkState.LOADED);
                responseLiveData.setValue(flightStatusResponse);
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


        return liveData;
    }

    private String getFlightDateCombo(String flightNumber, String date) {
        return String.format("%s+%s", flightNumber, getDate(date));
    }

    private String getDate(String date) {
        Date dateObj = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_PATTERN_USER_INPUT, Locale.getDefault());
        SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat(Constants.DATE_PATTERN_API_REQUEST_FLIGHT_STATUS, Locale.getDefault());
        try {
            dateObj = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar instance = Calendar.getInstance();
        if (dateObj != null) {
            instance.setTime(dateObj);
            return simpleDateFormatOutput.format(instance.getTime());
        }
        return "";
    }
}
