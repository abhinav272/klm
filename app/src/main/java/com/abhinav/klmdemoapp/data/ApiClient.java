package com.abhinav.klmdemoapp.data;

import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;
import com.abhinav.klmdemoapp.data.response.AllMasterDataResponse;
import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;
import com.abhinav.klmdemoapp.data.response.FlightStatusByOriginDestinationResponse;
import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;
import com.abhinav.klmdemoapp.data.response.OAuthResponse;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {


    @POST("/travel/oauth")
    Call<OAuthResponse> login(@Body RequestBody body);

    @Headers({
            "Accept: application/hal+json;version=com.afkl.operationalflight.v3",
            "Accept-Language: en-EN"
    })
    @GET("/travel/flightstatus/{flight}")
    Call<FlightStatusResponse> getStatusByFlight(@Path("flight") String flight, @Query("origin") String origin, @Query("expand") boolean ex);


    @Headers({
            "Accept: application/hal+json;version=com.afkl.operationalflight.v3"
    })
    @GET("/travel/flightstatus")
    Call<FlightStatusByOriginDestinationResponse> getStatusByOriginAndDestination(@Query("origin") String origin,
                                                                                  @Query("destination") String destination,
                                                                                  @Query("startRange") String startRange,
                                                                                  @Query("endRange") String endRange);

    @Headers({
            "Accept: application/hal+json;charset=UTF-8",
            "AFKL-TRAVEL-Host: KL",
            "AFKL-Travel-Market: NL",
            "Accept-Language: en-EN"
    })
    @GET("/travel/inspire/airports")
    Call<AllMasterDataResponse> getAllMasterData();

    @Headers({
            "Accept: application/hal+json;charset=UTF-8;profile=com.afklm.inspire.destinations.v4",
            "AFKL-TRAVEL-Host: KL",
            "AFKL-Travel-Market: NL",
            "Accept-Language: en-EN"
    })
    @GET("/travel/inspire/destinations/{code}/practicalinformation?fields=touristInformation,currency,spokenLanguages,time,weather&temperatureUnit=CELSIUS")
    Call<DestinationDetailsResponse> getDestinationDetails(@Path("code") String cityCode);

    @Headers({
            "Accept: application/hal+json;charset=UTF-8",
            "AFKL-TRAVEL-Host: KL",
            "AFKL-Travel-Market: NL",
            "Accept-Language: en-EN"
    })
    @GET("/travel/inspire/airports/{code}")
    Call<AirportCompleteInfoResponse> getDestinationInfo(@Path("code") String code);
}
