package com.abhinav.klmdemoapp.data;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.abhinav.klmdemoapp.BuildConfig;
import com.abhinav.klmdemoapp.data.response.AirportCompleteInfoResponse;
import com.abhinav.klmdemoapp.data.response.AllMasterDataResponse;
import com.abhinav.klmdemoapp.data.response.DestinationDetailsResponse;
import com.abhinav.klmdemoapp.data.response.FlightStatusByOriginDestinationResponse;
import com.abhinav.klmdemoapp.data.response.FlightStatusResponse;
import com.abhinav.klmdemoapp.data.response.OAuthResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiManager {

    private static ApiManager instance;
    private final String text = "grant_type=client_credentials";
    private final ApiClient apiClient;
    private final ApiClient apiClientUnAuth;
    private OkHttpClient.Builder httpClient;
    private OkHttpClient.Builder httpClientUnAuth;
    private Retrofit.Builder retrofitBuilder;
    private Retrofit.Builder retrofitBuilderUnAuth;
    private String newAuthToken;

    private ApiManager() {
        httpClientUnAuth = getUnAuthHttpClient();
        apiClientUnAuth = getUnAuthService();
        httpClient = getHttpClient();
        apiClient = getRetrofitService();
    }

    public static synchronized ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private OkHttpClient.Builder getUnAuthHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String credential = BuildConfig.USERNAME + ":" + BuildConfig.PASSWORD;
                        final String basic = "Basic " + Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP);
                        Request original = chain.request();
                        Request.Builder requestBuilder;
                        requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());
                        requestBuilder.header("Authorization", basic);
                        requestBuilder.header("Content-Type", "application/x-www-form-urlencoded");
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(getLoggingInterceptor())
                .readTimeout(35000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS);
    }

    private OkHttpClient.Builder getHttpClient() {
        return new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        if (response.code() == 401) {
                            Call<OAuthResponse> refreshCall = refreshAccessToken();
                            retrofit2.Response<OAuthResponse> execute = refreshCall.execute();
                            if (execute != null && execute.code() == 200) {
                                newAuthToken = execute.body().getAccessToken();
                                Log.e("authenticate: ", newAuthToken);
                                return response.request().newBuilder()
                                        .header("Authorization", "Bearer " + newAuthToken)
                                        .build();
                            }
                        }
                        return response.request();
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder;
                        requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());
                        requestBuilder.header("Authorization", "Bearer " + newAuthToken);
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(getLoggingInterceptor())
                .readTimeout(35000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS);
    }

    private Call<OAuthResponse> refreshAccessToken() {
        RequestBody body = RequestBody.create(MediaType.parse("text"), text);
        return apiClientUnAuth.login(body);
    }

    @NonNull
    private HttpLoggingInterceptor getLoggingInterceptor() {
        if (BuildConfig.DEBUG)
            return new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
        else return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private ApiClient getRetrofitService() {
        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.client(httpClient.build()).build();
        return retrofit.create(ApiClient.class);
    }

    private ApiClient getUnAuthService() {
        retrofitBuilderUnAuth = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilderUnAuth.client(httpClientUnAuth.build()).build();
        return retrofit.create(ApiClient.class);
    }

    public Call<OAuthResponse> doLogin() {
        RequestBody body = RequestBody.create(MediaType.parse("text"), text);
        return apiClientUnAuth.login(body);
    }

    public Call<FlightStatusResponse> getStatusByFlight(String flightDateCombo, String origin) {
//        return apiClient.getStatusByFlight("KL1001+2019-02-26", "AMS", false);
        return apiClient.getStatusByFlight(flightDateCombo, origin, false);
    }

    public Call<FlightStatusByOriginDestinationResponse> getStatusByOriginAndDestination(String origin, String destination, String dateStart, String dateEnd) {
        return apiClient.getStatusByOriginAndDestination(origin, destination, dateStart, dateEnd);
    }

    public Call<AllMasterDataResponse> fetchMasterData() {
        return apiClient.getAllMasterData();
    }

    public Call<DestinationDetailsResponse> getDestinationDetail(String code){
        return apiClient.getDestinationDetails(code);
    }

    public Call<AirportCompleteInfoResponse> getAirportDetails(String cityCode) {
        return apiClient.getDestinationInfo(cityCode);
    }
}
